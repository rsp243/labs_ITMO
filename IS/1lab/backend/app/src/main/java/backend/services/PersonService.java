package backend.services;

import org.springframework.stereotype.Service;

import backend.DTO.CoordinatesCreatedDTO;
import backend.DTO.CoordinatesEditDTO;
import backend.DTO.DeletedDTO;
import backend.DTO.PersonCreatedDTO;
import backend.DTO.PersonDTO;
import backend.DTO.PersonEditDTO;
import backend.DTO.PointsCreatedDTO;
// import backend.DTO.DeletedDTO;
import backend.DTO.TokenDTO;
import backend.exceptions.DoesNotExistException;
import backend.exceptions.ObjectNotFoundException;
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

    public List<Person> getAllPeople() {
        return peopleRepository.findAll();
    }

    public Person getMaxIdPerson() throws ObjectNotFoundException {
        List<Person> allPeople = getAllPeople();
        if (allPeople.size() < 1) {
            throw new ObjectNotFoundException("No objects in collection");
        }

        return allPeople.get(0);
    }

    public PersonCreatedDTO addPerson(PersonDTO req) throws DoesNotExistException {
        final long userId = jwtUtils.getIdFromToken(req.getToken().getToken());
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
        return Person.getCreatedPerson(person, owner.getId());
    }

    public DeletedDTO deletePerson(int personId) {
        Person person = peopleRepository.getReferenceById(Long.valueOf(personId));
        final Coordinates coordinates = person.getCoordinates();
        final Location location = person.getLocation();

        location.getPeople().remove(person);
        coordinates.getPeople().remove(person);
        peopleRepository.deleteById(Long.valueOf(personId));

        return new DeletedDTO("Successfully deleted.");
    }

    public Person getById(int id) throws ObjectNotFoundException {
        List<Person> people = this.getAllPeople(); 
        for (int i = 0; i < people.size(); i++) {
            if (id == people.get(i).getId()) {
                return people.get(i);
            }
        }

        throw new ObjectNotFoundException("Object wasn't found in database");
    }

    public PersonCreatedDTO editPerson(PersonEditDTO req) throws ObjectNotFoundException {
        Person person = peopleRepository.getReferenceById(Long.valueOf(req.getId()));
        final Coordinates coordinates = coordinatesRepository.getReferenceById(req.getCoordinates_id());
        final Location location = locationRepository.getReferenceById(req.getLocation_id());

        List<Person> coordinatesPeople = coordinates.getPeople();
        coordinatesPeople.remove(person);
        
        List<Person> locationPeople = location.getPeople();
        locationPeople.remove(person);

        person.setName(req.getName());
        person.setCoordinates(coordinates);
        person.setLocation(location);
        person.setEyeColor(req.getEye_color());
        person.setHairColor(req.getHair_color());
        person.setHeight(req.getHeight());
        person.setNationality(req.getNationality());
        
        peopleRepository.save(person);

        coordinatesPeople.add(person);
        coordinates.setPeople(coordinatesPeople);
        coordinatesRepository.save(coordinates);

        locationPeople.add(person);
        location.setPeople(locationPeople);
        locationRepository.save(location);
        
        return Person.getCreatedPerson(person, person.getUserId().getId());
    }
}
