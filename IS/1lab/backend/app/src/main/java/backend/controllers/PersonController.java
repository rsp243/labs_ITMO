package backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.LinkedList;

import backend.model.Person;
import backend.DTO.PersonCreatedDTO;
import backend.DTO.PersonDTO;
import backend.DTO.PointsCreatedDTO;
import backend.DTO.DeletedDTO;
import backend.DTO.TokenDTO;
import backend.DTO.UsersDTO;
import backend.exceptions.ApiException;
import backend.model.validators.TokenValidator;
import backend.model.validators.UsersValidator;
import backend.security.JwtUtils;
import backend.services.PersonService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/person", produces = { "application/json" })
public class PersonController {

    private final JwtUtils jwtUtils;
    private final PersonService personService;

    @PostMapping(path = "/all")
    public ResponseEntity<?> getAll(@RequestBody TokenDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());

        return ControllerExecutor.execute(validator, () -> {
            List<Person> allPeople = personService.getAllPeople();
            List<PersonCreatedDTO> result = new LinkedList<PersonCreatedDTO>();
            for (int i = 0; i < allPeople.size(); i++) {
                Person iPerson = allPeople.get(i);
                result.add(iPerson.getCreatedPerson(iPerson));
            }
            return ResponseEntity.ok().body(result);
        });
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addPoint(@RequestBody PersonDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req);

        return ControllerExecutor.execute(validator, () -> {
            PersonCreatedDTO personDTO = personService.addPerson(req);
            return ResponseEntity.ok().body(personDTO);
        });
    }

    // @PostMapping(path = "/delete")
    // public ResponseEntity<?> deleteAllPoints(@RequestBody TokenDTO req) {
    //     TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());

    //     return ControllerExecutor.execute(validator, () -> {
    //         PointsDeletedDTO pointDTO = personService.deleteAllPoints(
    //             personService.getAllPointsCreatedByUser(req));
    //         return ResponseEntity.ok().body(pointDTO);
    //     });
    // }
}
