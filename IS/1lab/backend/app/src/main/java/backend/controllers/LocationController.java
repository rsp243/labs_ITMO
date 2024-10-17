package backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.LinkedList;

import backend.DTO.CoordinatesCreatedDTO;
import backend.DTO.CoordinatesEditDTO;
import backend.DTO.DeletedDTO;
import backend.DTO.IdDTO;
import backend.DTO.LocationCreatedDTO;
import backend.DTO.LocationDTO;
import backend.DTO.LocationEditDTO;
import backend.DTO.PersonCreatedDTO;
import backend.DTO.TokenDTO;
import backend.exceptions.DoesNotExistException;
import backend.exceptions.ForbiddenException;
import backend.exceptions.ObjectNotFoundException;
import backend.model.Location;
import backend.model.Person;
import backend.model.validators.TokenValidator;
import backend.security.JwtUtils;
import backend.services.AdminService;
import backend.services.AuthService;
import backend.services.LocationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/location", produces = { "application/json" })
public class LocationController {

    private final JwtUtils jwtUtils;
    private final LocationService locationService;
    private final AdminService adminService;

    @PostMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id, @RequestBody TokenDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());

        return ControllerExecutor.execute(validator, () -> {
            LocationCreatedDTO result = Location.getCreatedLocation(locationService.getById(id));

            return ResponseEntity.ok().body(result);
        });
    }

    @PostMapping(path = "/all")
    public ResponseEntity<?> getAll(@RequestBody TokenDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());

        return ControllerExecutor.execute(validator, () -> {
            List<Location> allLocation = locationService.getAllLocation();
            List<LocationCreatedDTO> result = new LinkedList<LocationCreatedDTO>();
            for (int i = 0; i < allLocation.size(); i++) {
                Location iLocation = allLocation.get(i);
                result.add(Location.getCreatedLocation(iLocation));
            }
            return ResponseEntity.ok().body(result);
        });
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addLocation(@RequestBody LocationDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req);

        return ControllerExecutor.execute(validator, () -> {
            LocationCreatedDTO LocationDTO = locationService.addLocation(req);
            return ResponseEntity.ok().body(LocationDTO);
        });
    }

    @Transactional
    @PostMapping(path = "/delete")
    public ResponseEntity<?> deleteLocation(@RequestBody IdDTO req) throws ForbiddenException, ObjectNotFoundException, DoesNotExistException {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken().getToken());

        int user_id = jwtUtils.getIdFromToken(req.getToken().getToken());
        int location_id = req.getId();

        if (!adminService.isAdmin(user_id) && locationService.getById(location_id).getUserId().getId() != user_id) {
            throw new ForbiddenException("It's forbidden to you to delete this object.");
        }

        return ControllerExecutor.execute(validator, () -> {
            DeletedDTO locationDTO = locationService.deleteLocation(location_id);
            return ResponseEntity.ok().body(locationDTO);
        });
    }

    @PostMapping(path = "/edit")
    public ResponseEntity<?> editLocation(@RequestBody LocationEditDTO req) throws ForbiddenException, ObjectNotFoundException, DoesNotExistException {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken().getToken());

        int user_id = jwtUtils.getIdFromToken(req.getToken().getToken());
        int location_id = req.getId();

        if (!adminService.isAdmin(user_id) && locationService.getById(location_id).getUserId().getId() != user_id) {
            throw new ForbiddenException("It's forbidden to you to edit this object.");
        }

        return ControllerExecutor.execute(validator, () -> {
            LocationCreatedDTO coordinatesDTO = locationService.editLocation(req);
            return ResponseEntity.ok().body(coordinatesDTO);
        });
    }
}
