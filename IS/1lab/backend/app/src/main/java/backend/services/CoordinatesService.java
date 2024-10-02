package backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.DTO.CoordinatesCreatedDTO;
import backend.DTO.CoordinatesDTO;
import backend.exceptions.DoesNotExistException;
import backend.model.Coordinates;
import backend.model.Users;
import backend.repository.CoordinatesRepository;
import backend.repository.UserRepository;
import backend.security.JwtUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoordinatesService {

    private final CoordinatesRepository coordinatesRepository;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final AuthService authService;

    public List<Coordinates> getAllCoordinates() {
        return coordinatesRepository.findAll();
    }

    public CoordinatesCreatedDTO addCoordinates(CoordinatesDTO req) throws DoesNotExistException {
        final long userId = authService.getUserIdFromToken(req.getToken().getToken());
        final Users owner = userRepository.getReferenceById(userId);

        Coordinates coordinates = Coordinates.builder()
                .x(req.getX())
                .y(req.getY())
                .userId(owner)
                .build();

        coordinatesRepository.save(coordinates);
        return coordinates.getCreatedCoordinates(coordinates);
    }
}
