package backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.DTO.CoordinatesCreatedDTO;
import backend.DTO.CoordinatesDTO;
import backend.DTO.CoordinatesEditDTO;
import backend.DTO.DeletedDTO;
import backend.exceptions.DoesNotExistException;
import backend.exceptions.ObjectNotFoundException;
import backend.model.Coordinates;
import backend.model.Location;
import backend.model.Users;
import backend.repository.CoordinatesRepository;
import backend.repository.UserRepository;
import backend.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoordinatesService {

    private final CoordinatesRepository coordinatesRepository;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final AuthService authService;

    public List<Coordinates> getAllCoordinates() {
        return coordinatesRepository.findAll();
    }

    public CoordinatesCreatedDTO addCoordinates(CoordinatesDTO req) throws DoesNotExistException {
        final long userId = jwtUtils.getIdFromToken(req.getToken().getToken());
        final Users owner = userRepository.getReferenceById(userId);
        log.error("EDITABLE BY ADMIN " + req.isEditableByAdmin());

        Coordinates coordinates = Coordinates.builder()
                .x(req.getX())
                .y(req.getY())
                .userId(owner)
                .isEditableByAdmin(req.isEditableByAdmin())
                .build();

        coordinatesRepository.save(coordinates);
        return Coordinates.getCreatedCoordinates(coordinates, owner.getId());
    }

    public DeletedDTO deleteCoordinates(int coordinatesId) {
        coordinatesRepository.deleteById(coordinatesId);

        return new DeletedDTO("Successfully deleted.");
    }

    public Coordinates getById(int id) throws ObjectNotFoundException {
        List<Coordinates> coordinates = this.getAllCoordinates();
        for (int i = 0; i < coordinates.size(); i++) {
            if (id == coordinates.get(i).getId()) {
                return coordinates.get(i);
            }
        }

        throw new ObjectNotFoundException("Object wasn't found in database");
    }

    public CoordinatesCreatedDTO editCoordinates(CoordinatesEditDTO req) throws ObjectNotFoundException {
        Coordinates coordinates = coordinatesRepository.getReferenceById(Long.valueOf(req.getId()));
        coordinates.setX(req.getX());
        coordinates.setY(req.getY());
        coordinates.setEditableByAdmin(req.isEditableByAdmin());
        coordinatesRepository.save(coordinates);

        return Coordinates.getCreatedCoordinates(coordinates, coordinates.getUserId().getId());
    }
}
