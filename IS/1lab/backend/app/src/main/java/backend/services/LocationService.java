package backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.DTO.LocationCreatedDTO;
import backend.DTO.LocationDTO;
import backend.exceptions.DoesNotExistException;
import backend.model.Location;
import backend.model.Users;
import backend.repository.LocationRepository;
import backend.repository.UserRepository;
import backend.security.JwtUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {
    
    private final LocationRepository locationRepository;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final AuthService authService;

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public LocationCreatedDTO addLocation(LocationDTO req) throws DoesNotExistException {
        final long userId = authService.getUserIdFromToken(req.getToken().getToken());
        final Users owner = userRepository.getReferenceById(userId);

        Location location = Location.builder()
                .x(req.getX())
                .y(req.getY())
                .z(req.getZ())
                .userId(owner)
                .build();

        locationRepository.save(location);
        return location.getCreatedLocation(location);
    }
}
