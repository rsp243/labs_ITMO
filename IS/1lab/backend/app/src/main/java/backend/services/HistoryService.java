package backend.services;

import org.springframework.stereotype.Service;

import backend.DTO.HistoryCreatedDTO;
import backend.model.History;
import backend.model.Person;
import backend.repository.HistoryRepository;
import backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;

    public List<HistoryCreatedDTO> getLocationHistory(int id) {
        return historyRepository.findAllByObjectClassAndId("Location", id);
    }

    public List<HistoryCreatedDTO> getCoordinatesHistory(int id) {
        return historyRepository.findAllByObjectClassAndId("Coordinates", id);
    }
    
    public List<HistoryCreatedDTO> getPersonHistory(int id) {
        return historyRepository.findAllByObjectClassAndId("Person", id);
    }

    public void addCoordinatesHistory(int id, String userName) {
        History history = new History(id, userName, "Coordinates", LocalDateTime.now());

        historyRepository.save(history);
    }

    public void addLocationHistory(int id, String userName) {
        History history = new History(id, userName, "Location", LocalDateTime.now());
        
        historyRepository.save(history);
    }
    public void addPersonHistory(int id, String userName) {
        History history = new History(id, userName, "Person", LocalDateTime.now());
        
        historyRepository.save(history);
    }
}
