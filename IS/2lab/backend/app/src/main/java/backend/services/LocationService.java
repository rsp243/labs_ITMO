package backend.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import backend.DTO.CoordinatesCreatedDTO;
import backend.DTO.CoordinatesEditDTO;
import backend.DTO.DeletedDTO;
import backend.DTO.IdDTO;
import backend.DTO.LocationCreatedDTO;
import backend.DTO.LocationDTO;
import backend.DTO.LocationEditDTO;
import backend.exceptions.DoesNotExistException;
import backend.exceptions.ObjectNotFoundException;
import backend.model.Coordinates;
import backend.model.Location;
import backend.model.Users;
import backend.repository.LocationRepository;
import backend.repository.UserRepository;
import backend.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService {
    
    private final LocationRepository locationRepository;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final AuthService authService;

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public LocationCreatedDTO addLocation(LocationDTO req) throws DoesNotExistException {
        final long userId = jwtUtils.getIdFromToken(req.getToken().getToken());
        final Users owner = userRepository.getReferenceById(userId);
        log.error("EDITABLE BY ADMIN" + req.isEditableByAdmin());

        Location location = Location.builder()
                .x(req.getX())
                .y(req.getY())
                .z(req.getZ())
                .userId(owner)
                .isEditableByAdmin(req.isEditableByAdmin())
                .build();

        locationRepository.save(location);
        return Location.getCreatedLocation(location, owner.getId());
    }

    public DeletedDTO deleteLocation(int locationId) {
        locationRepository.deleteById(locationId);

        return new DeletedDTO("Successfully deleted.");
    }

    public Location getById(int id) throws ObjectNotFoundException {
        List<Location> locations = this.getAllLocation(); 
        for (int i = 0; i < locations.size(); i++) {
            if (id == locations.get(i).getId()) {
                return locations.get(i);
            }
        }

        throw new ObjectNotFoundException("Object wasn't found in database");
    }

    public LocationCreatedDTO editLocation(LocationEditDTO req) throws ObjectNotFoundException {
        Location location = locationRepository.getReferenceById(Long.valueOf(req.getId()));
        location.setX(req.getX());
        location.setY(req.getY());
        location.setZ(req.getZ());
        location.setEditableByAdmin(req.isEditableByAdmin());
        locationRepository.save(location);

        return Location.getCreatedLocation(location, location.getUserId().getId());
    }
}
