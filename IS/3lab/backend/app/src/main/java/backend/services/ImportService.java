package backend.services;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;

import backend.DTO.CoordinatesDTO;
import backend.DTO.ImportCreatedDTO;
import backend.DTO.LocationDTO;
import backend.DTO.PersonDTO;
import backend.DTO.TokenDTO;
import backend.exceptions.DoesNotExistException;
import backend.model.Color;
import backend.model.Coordinates;
import backend.model.Country;
import backend.model.Import;
import backend.model.ImportStatus;
import backend.model.Location;
import backend.model.Person;
import backend.model.Users;
import backend.repository.CoordinatesRepository;
import backend.repository.ImportRepository;
import backend.repository.LocationRepository;
import backend.repository.UserRepository;
import backend.security.JwtUtils;
import io.minio.errors.MinioException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportService {

    private final JwtUtils jwtUtils;
    private final AdminService adminService;
    private final UserRepository userRepository;

    private final ImportRepository importRepository;

    private final PersonService personService;
    private final CoordinatesService coordinatesService;
    private final LocationService locationService;
    private final HistoryService historyService;

    private final CoordinatesRepository coordinatesRepository;
    private final LocationRepository locationRepository;

    private final ImportLogger importLogger;

    private final S3Worker minioWorker;

    public List<ImportCreatedDTO> getImports(TokenDTO token) throws DoesNotExistException {
        final long user_id = jwtUtils.getIdFromToken(token.getToken());
        final boolean isAdmin = adminService.isAdmin(user_id);

        final Users owner = userRepository.getReferenceById(user_id);
        String username = owner.getName();

        List<Import> impList = importRepository.findAll();
        List<ImportCreatedDTO> result = new LinkedList<>();
        for (Import imp : impList) {
            result.add(Import.getCreatedImport(imp));
        }

        if (!isAdmin) {
            result.removeIf(imp -> !imp.getUserName().equals(username));
        }

        return result;
    }

    private ImportCreatedDTO createImportResponse(Class<?> failedClassToCreate, Import importObj) {
        return Import.getCreatedImport(importObj);
    }

    @Transactional
    public Import addImport(ImportStatus status, String userName, int count) {
        Import importObj = Import.builder()
                .status(status)
                .userName(userName)
                .count(count)
                .time(LocalDateTime.now()).build();
        
        log.info("Creating Import db table row" + importObj.toString());
        importLogger.writeLog("Creating Import database table row '" + importObj.toString() + "'");

        importRepository.save(importObj);

        return importObj;
    }

    public ImportCreatedDTO processYamlFile(MultipartFile file, TokenDTO token)
            throws IOException, DoesNotExistException, InvalidKeyException, NoSuchAlgorithmException, IllegalArgumentException, MinioException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }

        Yaml yaml = new Yaml();

        importLogger.openImportLogsFile();

        final long userId = jwtUtils.getIdFromToken(token.getToken());
        final Users owner = userRepository.getReferenceById(userId);
        String username = owner.getName();

        int count = 0;
        try (InputStream inputStream = file.getInputStream()) {
            importLogger.writeLog("Reading Import file");
            LinkedHashMap<String, List<Map<String, Object>>> parsedFile = yaml.load(inputStream);

            Boolean foundAny = false;
            List<Map<String, Object>> coordinatesList = parsedFile.get("coordinates");
            importLogger.writeLog("Checking if where is 'coordinates' objects in yaml");
            int coordinatesCount = 0;
            if (coordinatesList != null && !coordinatesList.isEmpty()) {
                log.info(coordinatesList.toString());
                foundAny = true;
                for (Map<String, Object> coordinates : coordinatesList) {
                    Map<String, String> errors = validateCoordinates(coordinates);
                    if (!errors.isEmpty()) {
                        Import importObj = addImport(ImportStatus.FAILED, username, count);
                        String objectName = createImportResponse(Coordinates.class, importObj).getObjectName();
                        minioWorker.uploadLogFile(objectName);
                        throw new ValidationException("Validation errors for coordinates: " + errors);
                    }
                    count++;
                    coordinatesCount++;

                    saveCoordinates(coordinates, username, token);
                }
            }
            importLogger.writeLog("Found " + coordinatesCount + " Coordinates objects");

            List<Map<String, Object>> locationList = parsedFile.get("locations");
            importLogger.writeLog("Checking if where is 'location' objects in yaml");
            int locationCount = 0;
            if (locationList != null && !locationList.isEmpty()) {
                log.info(locationList.toString());
                foundAny = true;
                for (Map<String, Object> location : locationList) {
                    Map<String, String> errors = validateLocation(location);
                    if (!errors.isEmpty()) {
                        Import importObj = addImport(ImportStatus.FAILED, username, count);
                        String objectName = createImportResponse(Location.class, importObj).getObjectName();
                        minioWorker.uploadLogFile(objectName);
                        throw new ValidationException("Validation errors for location: " + errors);
                    }
                    count++;
                    locationCount++;

                    saveLocation(location, username, token);
                }
            }
            importLogger.writeLog("Found " + locationCount + " Location objects");

            List<Map<String, Object>> peopleList = parsedFile.get("people");
            importLogger.writeLog("Checking if where is 'location' objects in yaml");
            int personsCount = 0;

            if (peopleList != null && !peopleList.isEmpty()) {
                log.info(peopleList.toString());
                foundAny = true;
                for (Map<String, Object> person : peopleList) {
                    Map<String, String> errors = validatePerson(person);
                    if (!errors.isEmpty()) {
                        Import importObj = addImport(ImportStatus.FAILED, username, count);
                        String objectName = createImportResponse(Person.class, importObj).getObjectName();
                        minioWorker.uploadLogFile(objectName);
                        throw new ValidationException("Validation errors for person: " + errors);
                    }
                    count += 3;
                    personsCount++;

                    savePerson(person, username, token);
                }
            }
            importLogger.writeLog("Found " + personsCount + " Person objects");

            if (!foundAny) {
                Map<String, String> errors = new HashMap<>();
                errors.put("Not found", "Not found nor coordinates, nor locations, nor people.");
                importLogger.writeLog("Not found nor coordinates, nor locations, nor people in passed yaml file");
                Import importObj = addImport(ImportStatus.FAILED, username, count);
                String objectName = createImportResponse(Person.class, importObj).getObjectName();

                minioWorker.uploadLogFile(objectName);
                throw new ValidationException("Not found: " + errors);
            }
        } catch (IOException e) {
            importLogger.writeLog("Failed to process YAML file: " + e.toString());
            throw new IOException("Failed to process YAML file", e);
        }

        Import importObj = addImport(ImportStatus.SUCCESSFUL, username, count);
        importLogger.writeLog("Successfully processed file.");
        ImportCreatedDTO res = Import.getCreatedImport(importObj);

        minioWorker.uploadLogFile(res.getObjectName());
        return res;
    }

    @Transactional
    private long saveCoordinates(Map<String, Object> coordinates, String username, TokenDTO token)
            throws DoesNotExistException {
        Long coordinatesX = ((Integer) coordinates.get("x")).longValue();
        Double coordinatesY = (double) coordinates.get("y");
        List<Coordinates> coordinatesCreatedList = coordinatesRepository.findByXAndY(coordinatesX, coordinatesY);
        if (!coordinatesCreatedList.isEmpty()) {
            return coordinatesCreatedList.get(0).getId();
        }
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();

        Boolean coordinatesEditableByAdmin = (coordinates.get("editableByAdmin") != null)
                ? ((boolean) coordinates.get("editableByAdmin"))
                : false;
        coordinatesDTO.setX(coordinatesX);
        coordinatesDTO.setY(coordinatesY);
        coordinatesDTO.setEditableByAdmin(coordinatesEditableByAdmin);
        coordinatesDTO.setToken(token);
        importLogger.writeLog("Creating coordinates object: " + coordinatesDTO.toString());

        Long coordinates_id = (long) coordinatesService.addCoordinates(coordinatesDTO).getId();
        importLogger.writeLog("Successfully created");

        importLogger.writeLog("Creating history row: " + coordinatesDTO.toString());
        historyService.addCoordinatesHistory(coordinates_id.intValue(), username);
        importLogger.writeLog("Successfully created");

        return coordinates_id;
    }

    @Transactional
    private long saveLocation(Map<String, Object> location, String username, TokenDTO token)
            throws DoesNotExistException {
        Integer locationX = (int) location.get("x");
        Integer locationY = (int) location.get("y");
        Long locationZ = ((Integer) location.get("z")).longValue();
        List<Location> locationCreatedList = locationRepository.findAllByXAndYAndZ(locationX, locationY, locationZ);
        if (!locationCreatedList.isEmpty()) {
            return locationCreatedList.get(0).getId();
        }
        LocationDTO locationDTO = new LocationDTO();

        Boolean locationEditableByAdmin = (location.get("editableByAdmin") != null)
                ? ((boolean) location.get("editableByAdmin"))
                : false;
        locationDTO.setX(locationX);
        locationDTO.setY(locationY);
        locationDTO.setZ(locationZ);
        locationDTO.setEditableByAdmin(locationEditableByAdmin);
        locationDTO.setToken(token);
        importLogger.writeLog("Creating location object: " + locationDTO.toString());

        Long location_id = (long) locationService.addLocation(locationDTO).getId();
        importLogger.writeLog("Successfully created");

        importLogger.writeLog("Creating history row: " + locationDTO.toString());
        historyService.addLocationHistory(location_id.intValue(), username);
        importLogger.writeLog("Successfully created");

        return location_id;
    }

    @Transactional
    private int savePerson(Map<String, Object> person, String username, TokenDTO token) throws DoesNotExistException {
        @SuppressWarnings("unchecked")
        Map<String, Object> coordinates = (Map<String, Object>) person.get("coordinates");
        long coordinates_id = saveCoordinates(coordinates, username, token);

        @SuppressWarnings("unchecked")
        Map<String, Object> location = (Map<String, Object>) person.get("location");
        long location_id = saveLocation(location, username, token);

        String name = (String) person.get("name");
        Integer height = (int) person.get("height");
        Boolean editableByAdmin = (person.get("editableByAdmin") != null) ? ((boolean) person.get("editableByAdmin"))
                : false;
        Color eyeColor = Color.valueOf(((String) person.get("eyeColor")).toUpperCase());
        Color hairColor = Color.valueOf(((String) person.get("hairColor")).toUpperCase());
        Country nationality = Country.valueOf(((String) person.get("nationality")).toUpperCase());

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(name);
        personDTO.setHeight(height);
        personDTO.setEditableByAdmin(editableByAdmin);
        personDTO.setEye_color(eyeColor);
        personDTO.setHair_color(hairColor);
        personDTO.setNationality(nationality);
        personDTO.setToken(token);
        personDTO.setCoordinates_id(coordinates_id);
        personDTO.setLocation_id(location_id);
        importLogger.writeLog("Creating Person object: " + personDTO.toString());

        Integer person_id = (int) personService.addPerson(personDTO).getId();
        importLogger.writeLog("Successfully created");

        importLogger.writeLog("Creating history row: " + personDTO.toString());
        historyService.addPersonHistory(person_id, username);
        importLogger.writeLog("Successfully created");

        return person_id;
    }

    Map<String, String> validateLocation(Map<String, Object> location) {
        Map<String, String> errors = new HashMap<>();
        if (!location.containsKey("x") || !(location.get("x") instanceof Integer)) {
            errors.put("location.x", "Location x should be integer.");
        }
        if (!location.containsKey("y") || !(location.get("y") instanceof Integer)) {
            errors.put("location.y", "Location y should be integer.");
        }
        if (!location.containsKey("z") || !(location.get("z") instanceof Integer)) {
            errors.put("location.z", "Location z should be integer.");
        }
        if (location.containsKey("editableByAdmin") && !(location.get("editableByAdmin") instanceof Boolean)) {
            errors.put("LocationEditableByAdmin", "editableByAdmin should be Boolean object.");
        }

        return errors;
    }

    Map<String, String> validateCoordinates(Map<String, Object> coordinates) {
        Map<String, String> errors = new HashMap<>();
        if (!coordinates.containsKey("x") || !(coordinates.get("x") instanceof Integer)) {
            errors.put("coordinates.x", "Coordinates x should be integer.");
        }
        if (!coordinates.containsKey("y") || !(coordinates.get("y") instanceof Double)) {
            errors.put("coordinates.y", "Coordinates y should be double.");
        }
        if (coordinates.containsKey("editableByAdmin")
                && !(coordinates.get("editableByAdmin") instanceof Boolean)) {
            errors.put("CoordinatesEditableByAdmin", "editableByAdmin should be Boolean object.");
        }

        return errors;
    }

    Map<String, String> validatePerson(Map<String, Object> person) {
        Map<String, String> errors = new HashMap<>();

        if (!person.containsKey("name") || person.get("name") == null || ((String) person.get("name")).isEmpty()) {
            errors.put("name", "Name cannot be null or empty.");
        }

        if (!person.containsKey("coordinates") || !(person.get("coordinates") instanceof Map)) {
            errors.put("coordinates", "Coordinates must be provided and must be a valid object.");
        } else {
            @SuppressWarnings("unchecked")
            Map<String, Object> coordinates = (Map<String, Object>) person.get("coordinates");
            errors.putAll(validateCoordinates(coordinates));
        }

        if (!person.containsKey("hairColor") || !(person.get("hairColor") instanceof String)
                || !isValidEnumValue((String) person.get("hairColor"), Color.class)) {
            errors.put("hairColor", "Hair color should be one of the color enum values.");
        }

        if (!person.containsKey("height") || !(person.get("height") instanceof Integer)
                || ((Number) person.get("height")).intValue() <= 0) {
            errors.put("height", "Height must be greater than 0.");
        }

        if (!person.containsKey("location") || !(person.get("location") instanceof Map)) {
            errors.put("location", "Location must be provided and must be a valid object.");
        } else {
            @SuppressWarnings("unchecked")
            Map<String, Object> location = (Map<String, Object>) person.get("location");
            errors.putAll(validateLocation(location));
        }
        if (!person.containsKey("eyeColor") || !(person.get("eyeColor") instanceof String)
                || !isValidEnumValue((String) person.get("eyeColor"), Color.class)) {
            errors.put("eyeColor", "Eye color should be one of the color enum values.");
        }

        if (!person.containsKey("nationality") || !(person.get("nationality") instanceof String)
                || !isValidEnumValue((String) person.get("nationality"), Country.class)) {
            errors.put("nationality", "Nationality should be one of the nationality enum values.");
        }

        if (person.containsKey("editableByAdmin") && !(person.get("editableByAdmin") instanceof Boolean)) {
            errors.put("PersonEditableByAdmin", "editableByAdmin should be Boolean object.");
        }

        return errors;
    }

    private <E extends Enum<E>> boolean isValidEnumValue(String value, Class<E> enumClass) {
        try {
            Enum.valueOf(enumClass, value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
