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

import backend.DTO.CoordinatesCreatedDTO;
import backend.DTO.CoordinatesDTO;
import backend.DTO.CoordinatesEditDTO;
import backend.DTO.DeletedDTO;
import backend.DTO.HistoryCreatedDTO;
import backend.DTO.IdDTO;
import backend.DTO.LocationCreatedDTO;
import backend.DTO.PersonCreatedDTO;
import backend.DTO.PersonDTO;
import backend.DTO.TokenDTO;
import backend.exceptions.DoesNotExistException;
import backend.exceptions.ForbiddenException;
import backend.exceptions.ObjectNotFoundException;
import backend.model.Coordinates;
import backend.model.Location;
import backend.model.Person;
import backend.model.validators.TokenValidator;
import backend.security.JwtUtils;
import backend.services.AdminService;
import backend.services.CoordinatesService;
import backend.services.HistoryService;
import backend.services.PersonService;
import backend.services.UsersService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
        @RequestMapping(path = "/api/v1/coordinates", produces = { "application/json" })
        public class CoordinatesController {

            private final JwtUtils jwtUtils;
            private final CoordinatesService coordinatesService;
            private final AdminService adminService;
            private final HistoryService historyService;
            private final UsersService usersService;

            @PostMapping(path = "/{id}")
            public ResponseEntity<?> getById(@PathVariable("id") int id, @RequestBody TokenDTO req) {
                TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());
                int user_id = jwtUtils.getIdFromToken(req.getToken());

                return ControllerExecutor.execute(validator, () -> {
                    CoordinatesCreatedDTO result = Coordinates.getCreatedCoordinates(coordinatesService.getById(id), user_id);

                    return ResponseEntity.ok().body(result);
                });
            }

            @PostMapping(path = "/all")
            public ResponseEntity<?> getAll(@RequestBody TokenDTO req) {
                TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());
                int user_id = jwtUtils.getIdFromToken(req.getToken());

                return ControllerExecutor.execute(validator, () -> {
                    List<Coordinates> allCoordinates = coordinatesService.getAllCoordinates();
                    List<CoordinatesCreatedDTO> result = new LinkedList<CoordinatesCreatedDTO>();
                    for (int i = 0; i < allCoordinates.size(); i++) {
                        Coordinates iCoordinates = allCoordinates.get(i);
                        result.add(Coordinates.getCreatedCoordinates(iCoordinates, user_id));
                    }
                    return ResponseEntity.ok().body(result);
                });
            }

            @PostMapping(path = "/add")
            public ResponseEntity<?> addPoint(@RequestBody CoordinatesDTO req) throws NotFoundException {
                TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req);

                int user_id = jwtUtils.getIdFromToken(req.getToken().getToken());
                String username = usersService.getById(user_id).getName();

                return ControllerExecutor.execute(validator, () -> {
                    CoordinatesCreatedDTO coordinatesDTO = coordinatesService.addCoordinates(req);
                    historyService.addCoordinatesHistory(coordinatesDTO.getId(), username);

                    return ResponseEntity.ok().body(coordinatesDTO);
                });
            }

            @Transactional
            @PostMapping(path = "/delete")
            public ResponseEntity<?> deleteCoordinates(@RequestBody IdDTO req)
                    throws ForbiddenException, ObjectNotFoundException, NotFoundException {
                TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken().getToken());

                int user_id = jwtUtils.getIdFromToken(req.getToken().getToken());
                int coordinates_id = req.getId();

                if (coordinatesService.getById(coordinates_id).getUserId().getId() != user_id) {
                    throw new ForbiddenException("It's forbidden to you to delete this object.");
                }
                String username = usersService.getById(user_id).getName();

                return ControllerExecutor.execute(validator, () -> {
                    DeletedDTO coordinatesDTO = coordinatesService.deleteCoordinates(coordinates_id);
                    historyService.deleteCoordinatesHistory(coordinates_id);

                    return ResponseEntity.ok().body(coordinatesDTO);
                });
            }

            @PostMapping(path = "/edit")
            public ResponseEntity<?> editCoordinates(@RequestBody CoordinatesEditDTO req)
                    throws ForbiddenException, ObjectNotFoundException, DoesNotExistException, NotFoundException {
                TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken().getToken());

                int user_id = jwtUtils.getIdFromToken(req.getToken().getToken());
                int coordinates_id = req.getId();

                if (!adminService.isAdmin(user_id)
                        && coordinatesService.getById(coordinates_id).getUserId().getId() != user_id) {
                    throw new ForbiddenException("It's forbidden to you to edit this object.");
                }
                String username = usersService.getById(user_id).getName();

                return ControllerExecutor.execute(validator, () -> {
                    CoordinatesCreatedDTO coordinatesDTO = coordinatesService.editCoordinates(req);
                    historyService.addCoordinatesHistory(coordinates_id, username);

                    return ResponseEntity.ok().body(coordinatesDTO);
                });
            }

    @PostMapping(path = "/history")
    public ResponseEntity<?> getHistory(@RequestBody IdDTO req)
            throws ForbiddenException, ObjectNotFoundException, DoesNotExistException {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken().getToken());

        return ControllerExecutor.execute(validator, () -> {
            List<HistoryCreatedDTO> res = historyService.getCoordinatesHistory(req.getId());

            return ResponseEntity.ok().body(res);
        });
    }
}
