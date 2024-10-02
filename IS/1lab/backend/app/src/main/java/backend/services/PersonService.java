package backend.services;

import org.springframework.stereotype.Service;

import backend.DTO.PersonCreatedDTO;
import backend.DTO.PersonDTO;
import backend.DTO.PointsCreatedDTO;
// import backend.DTO.DeletedDTO;
import backend.DTO.TokenDTO;
import backend.exceptions.DoesNotExistException;
import backend.exceptions.TokenNotPassedException;
import backend.model.Coordinates;
import backend.model.Location;
import backend.model.Person;
import backend.model.Users;
import backend.repository.CoordinatesRepository;
import backend.repository.LocationRepository;
import backend.repository.PersonRepository;
import backend.repository.UserRepository;
import backend.security.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedList;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository peopleRepository;
    // private final AuthentificatedMap authentificatedMap;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final LocationRepository locationRepository;
    private final AuthService authService;

    public List<Person> getAllPeople() {
        return peopleRepository.findAll();
    }

    public PersonCreatedDTO addPerson(PersonDTO req) throws DoesNotExistException {
        final long userId = authService.getUserIdFromToken(req.getToken().getToken());
        final Users owner = userRepository.getReferenceById(userId);

        final Coordinates coordinates = coordinatesRepository.getReferenceById(req.getCoordinates_id());
        final Location location = locationRepository.getReferenceById(req.getLocation_id());

        java.time.ZoneId zid = java.time.ZoneId.of("Europe/Moscow");

        Person person = Person.builder()
                .name(req.getName())
                .coordinates(coordinates)
                .creationDate(java.time.LocalDate.now(zid))
                .eyeColor(req.getEye_color())
                .hairColor(req.getHair_color())
                .location(location)
                .height(req.getHeight())
                .nationality(req.getNationality())
                .userId(owner)
                .build();


        peopleRepository.save(person);
        return person.getCreatedPerson(person);
    }

    // public boolean checkArea(float xValue, float yValue, float rValue) {
    //     boolean inCircle = checkCircle(xValue, yValue, rValue);
    //     boolean inTriangle = checkTriangle(xValue, yValue, rValue);
    //     boolean inRectangle = checkRectangle(xValue, yValue, rValue);
    //     return inCircle || inTriangle || inRectangle;
    // }

    // private static boolean checkCircle(final float xValue, final float yValue, final float rValue) {
    //     return xValue > 0 && yValue > 0 && sqrt(pow(xValue, 2) + pow(yValue, 2)) <= rValue / 2;
    // }

    // private static boolean checkTriangle(final float xValue, final float yValue, final float rValue) {
    //     return xValue <= 0 && yValue <= 0 && abs(xValue) + abs(yValue) / 2 <= rValue / 2;
    // }

    // private static boolean checkRectangle(final float xValue, final float yValue, final float rValue) {
    //     return xValue <= 0 && yValue >= 0 && abs(xValue) <= rValue && yValue <= rValue / 2;
    // }

    // private static boolean validateXYR(float x, float y, float r) {
    //     return x >= -2 && x <= 2 && y >= -5 && y <= 5 && r >= -2 && r <= 2;
    // }

    // public boolean getResult(float x, float y, float r) {
    //     return validateXYR(x, y, r) && checkArea(x, y, r);
    // }

    // public void deletePoint(long pointId) {
    //     pointsRepository.deleteById(pointId);
    // }

    // public PointsDeletedDTO deleteAllPoints(List<Points> pointsList) {
    //     pointsRepository.deleteAll(pointsList);

    //     return new PointsDeletedDTO("Deleted successfully.");
    // }
}
