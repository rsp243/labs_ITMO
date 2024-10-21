package backend.controllers;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.LinkedList;

import backend.model.Person;
import backend.DTO.PersonCreatedDTO;
import backend.DTO.PersonDTO;
import backend.DTO.PersonEditDTO;
import backend.DTO.CoordinatesCreatedDTO;
import backend.DTO.CoordinatesEditDTO;
import backend.DTO.DeletedDTO;
import backend.DTO.HistoryCreatedDTO;
import backend.DTO.IdDTO;
import backend.DTO.TokenDTO;
import backend.DTO.UsersDTO;
import backend.exceptions.ApiException;
import backend.exceptions.DoesNotExistException;
import backend.exceptions.ForbiddenException;
import backend.exceptions.ObjectNotFoundException;
import backend.model.validators.TokenValidator;
import backend.model.validators.UsersValidator;
import backend.security.JwtUtils;
import backend.services.AdminService;
import backend.services.AuthService;
import backend.services.HistoryService;
import backend.services.PersonService;
import backend.services.UsersService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/person", produces = { "application/json" })
public class PersonController {

    private final JwtUtils jwtUtils;
    private final PersonService personService;
    private final AdminService adminService;
    private final HistoryService historyService;
    private final UsersService usersService;

    @PostMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id, @RequestBody TokenDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());
        int user_id = jwtUtils.getIdFromToken(req.getToken());

        return ControllerExecutor.execute(validator, () -> {
            PersonCreatedDTO result = Person.getCreatedPerson(personService.getById(id), user_id);

            return ResponseEntity.ok().body(result);
        });
    }

    @PostMapping(path = "/max_id")
    public ResponseEntity<?> getMaxIdPerson(@RequestBody TokenDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());
        int user_id = jwtUtils.getIdFromToken(req.getToken());

        return ControllerExecutor.execute(validator, () -> {
            PersonCreatedDTO result = Person.getCreatedPerson(personService.getMaxIdPerson(), user_id);

            return ResponseEntity.ok().body(result);
        });
    }

    @PostMapping(path = "/all")
    public ResponseEntity<?> getAll(@RequestBody TokenDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());
        int user_id = jwtUtils.getIdFromToken(req.getToken());
        
        return ControllerExecutor.execute(validator, () -> {
            List<Person> allPeople = personService.getAllPeople();
            List<PersonCreatedDTO> result = new LinkedList<PersonCreatedDTO>();
            for (int i = 0; i < allPeople.size(); i++) {
                Person iPerson = allPeople.get(i);
                result.add(Person.getCreatedPerson(iPerson, user_id));
            }

            return ResponseEntity.ok().body(result);
        });
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addPerson(@RequestBody PersonDTO req) throws NotFoundException {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req);

        int user_id = jwtUtils.getIdFromToken(req.getToken().getToken());
        String username = usersService.getById(user_id).getName();

        return ControllerExecutor.execute(validator, () -> {
            PersonCreatedDTO personDTO = personService.addPerson(req);
            historyService.addPersonHistory(personDTO.getId(), username);

            return ResponseEntity.ok().body(personDTO);
        });
    }

    @Transactional
    @PostMapping(path = "/delete")
    public ResponseEntity<?> deletePerson(@RequestBody IdDTO req) throws ForbiddenException, ObjectNotFoundException, DoesNotExistException, NotFoundException {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken().getToken());

        int user_id = jwtUtils.getIdFromToken(req.getToken().getToken());
        int person_id = req.getId();

        Person person = personService.getById(person_id);
        if (!(adminService.isAdmin(user_id) && person.isEditableByAdmin()) && person.getUserId().getId() != user_id) {
            throw new ForbiddenException("It's forbidden to you to delete this object.");
        }
        String username = usersService.getById(user_id).getName();

        return ControllerExecutor.execute(validator, () -> {
            DeletedDTO personDTO = personService.deletePerson(person_id);
            historyService.addPersonHistory(person_id, username);

            return ResponseEntity.ok().body(personDTO);
        });
    }

    @PostMapping(path = "/edit")
    public ResponseEntity<?> editPerson(@RequestBody PersonEditDTO req) throws ForbiddenException, ObjectNotFoundException, DoesNotExistException, NotFoundException {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken().getToken());

        int user_id = jwtUtils.getIdFromToken(req.getToken().getToken());
        int person_id = req.getId();

        Person person = personService.getById(person_id);
        if (!(adminService.isAdmin(user_id) && person.isEditableByAdmin()) && person.getUserId().getId() != user_id) {
            throw new ForbiddenException("It's forbidden to you to edit this object.");
        }
        String username = usersService.getById(user_id).getName();

        return ControllerExecutor.execute(validator, () -> {
            PersonCreatedDTO personDTO = personService.editPerson(req);
            historyService.addPersonHistory(person_id, username);

            return ResponseEntity.ok().body(personDTO);
        });
    }

    @PostMapping(path = "/history")
    public ResponseEntity<?> getHistory(@RequestBody IdDTO req) throws ForbiddenException, ObjectNotFoundException, DoesNotExistException {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken().getToken());

        return ControllerExecutor.execute(validator, () -> {
            List<HistoryCreatedDTO> res = historyService.getPersonHistory(req.getId());

            return ResponseEntity.ok().body(res);
        });
    }
}
