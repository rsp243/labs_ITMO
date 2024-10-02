package backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.LinkedList;

import backend.DTO.DeletedDTO;
import backend.DTO.IdDTO;
import backend.DTO.LocationCreatedDTO;
import backend.DTO.LocationDTO;
import backend.DTO.TokenDTO;
import backend.model.Location;
import backend.model.validators.TokenValidator;
import backend.security.JwtUtils;
import backend.services.LocationService;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/location", produces = { "application/json" })
public class LocationController {

    private final JwtUtils jwtUtils;
    private final LocationService locationService;

    @PostMapping(path = "/all")
    public ResponseEntity<?> getAll(@RequestBody TokenDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());

        return ControllerExecutor.execute(validator, () -> {
            List<Location> allLocation = locationService.getAllLocation();
            List<LocationCreatedDTO> result = new LinkedList<LocationCreatedDTO>();
            for (int i = 0; i < allLocation.size(); i++) {
                Location iLocation = allLocation.get(i);
                result.add(iLocation.getCreatedLocation(iLocation));
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
    
    @PostMapping(path = "/delete")
    public ResponseEntity<?> deleteLocation(@RequestBody IdDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken().getToken());

        int user_id = jwtUtils.getIdFromToken(req.getToken().getToken());
        int location_id = req.getId();
        if (locationService.getAllLocationCreatedByUser(user_id).contains(location_id)) {
            validator.addViolation("affiliation", "Location doesn't affiliate to user");
        }

        return ControllerExecutor.execute(validator, () -> {
            DeletedDTO locationDTO = locationService.deleteLocation(location_id);
            return ResponseEntity.ok().body(locationDTO);
        });
    }
}
