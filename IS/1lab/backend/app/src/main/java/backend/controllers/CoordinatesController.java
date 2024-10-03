package backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.LinkedList;

import backend.DTO.CoordinatesCreatedDTO;
import backend.DTO.CoordinatesDTO;
import backend.DTO.DeletedDTO;
import backend.DTO.IdDTO;
import backend.DTO.PersonCreatedDTO;
import backend.DTO.PersonDTO;
import backend.DTO.TokenDTO;
import backend.exceptions.ForbiddenException;
import backend.exceptions.ObjectNotFoundException;
import backend.model.Coordinates;
import backend.model.Person;
import backend.model.validators.TokenValidator;
import backend.security.JwtUtils;
import backend.services.CoordinatesService;
import backend.services.PersonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/coordinates", produces = { "application/json" })
public class CoordinatesController {
    
    private final JwtUtils jwtUtils;
    private final CoordinatesService coordinatesService;

    @PostMapping(path = "/all")
    public ResponseEntity<?> getAll(@RequestBody TokenDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());

        return ControllerExecutor.execute(validator, () -> {
            List<Coordinates> allCoordinates = coordinatesService.getAllCoordinates();
            List<CoordinatesCreatedDTO> result = new LinkedList<CoordinatesCreatedDTO>();
            for (int i = 0; i < allCoordinates.size(); i++) {
                Coordinates iCoordinates = allCoordinates.get(i);
                result.add(iCoordinates.getCreatedCoordinates(iCoordinates));
            }
            return ResponseEntity.ok().body(result);
        });
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addPoint(@RequestBody CoordinatesDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req);

        return ControllerExecutor.execute(validator, () -> {
            CoordinatesCreatedDTO coordinatesDTO = coordinatesService.addCoordinates(req);
            return ResponseEntity.ok().body(coordinatesDTO);
        });
    }

    @Transactional
    @PostMapping(path = "/delete")
    public ResponseEntity<?> deleteCoordinates(@RequestBody IdDTO req) throws ForbiddenException, ObjectNotFoundException {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken().getToken());

        int user_id = jwtUtils.getIdFromToken(req.getToken().getToken());
        int coordinates_id = req.getId();

        if (coordinatesService.getById(coordinates_id).getUserId().getId() != user_id) {
            throw new ForbiddenException("It's forbidden to you to delete this object.");
        }

        return ControllerExecutor.execute(validator, () -> {
            DeletedDTO coordinatesDTO = coordinatesService.deleteCoordinates(coordinates_id);
            return ResponseEntity.ok().body(coordinatesDTO);
        });
    }
}
