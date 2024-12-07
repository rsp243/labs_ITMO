package backend.services;

import org.springframework.stereotype.Service;

import backend.DTO.DeletedDTO;
import backend.DTO.HistoryCreatedDTO;
import backend.model.History;
import backend.repository.HistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;

    public List<HistoryCreatedDTO> getLocationHistory(int id) {
        List<History> historyList = historyRepository.findAllByObjectClassAndIdObj("Location", id);
        List<HistoryCreatedDTO> dtoList = new LinkedList<>(); 
        for (History history : historyList) {
            dtoList.add(History.getCreatedHistory(history));
        }
        
        return dtoList;
    }

    public List<HistoryCreatedDTO> getCoordinatesHistory(int id) {
        List<History> historyList = historyRepository.findAllByObjectClassAndIdObj("Coordinates", id);
        List<HistoryCreatedDTO> dtoList = new LinkedList<>(); 
        for (History history : historyList) {
            dtoList.add(History.getCreatedHistory(history));
        }
        
        return dtoList;        
    }

    public List<HistoryCreatedDTO> getPersonHistory(int id) {
        List<History> historyList = historyRepository.findAllByObjectClassAndIdObj("Person", id);
        List<HistoryCreatedDTO> dtoList = new LinkedList<>(); 
        for (History history : historyList) {
            dtoList.add(History.getCreatedHistory(history));
        }

        return dtoList;
    }

    @Transactional
    public DeletedDTO deleteCoordinatesHistory(int id) {
        List<History> historyToDeleteList = historyRepository.findAllByObjectClassAndIdObj("Coordinates", id);
        for (History history : historyToDeleteList) {
            historyRepository.deleteById((long) history.getId());
        }

        return new DeletedDTO("Successfully deleted.");
    }
    
    @Transactional
    public DeletedDTO deleteLocationHistory(int id) {
        List<History> historyToDeleteList = historyRepository.findAllByObjectClassAndIdObj("Location", id);
        for (History history : historyToDeleteList) {
            historyRepository.deleteById((long) history.getId());
        }

        return new DeletedDTO("Successfully deleted.");
    }

    @Transactional
    public DeletedDTO deletePersonHistory(int id) {
        List<History> historyToDeleteList = historyRepository.findAllByObjectClassAndIdObj("Person", id);
        for (History history : historyToDeleteList) {
            historyRepository.deleteById((long) history.getId());
        }

        return new DeletedDTO("Successfully deleted.");
    }

    public void addCoordinatesHistory(int id, String userName) {
        History history = History.builder()
                .idObj(id)
                .objectClass("Coordinates")
                .time(LocalDateTime.now())
                .userName(userName).build();

        historyRepository.save(history);
    }

    public void addLocationHistory(int id, String userName) {
        History history = History.builder()
                .idObj(id)
                .objectClass("Location")
                .time(LocalDateTime.now())
                .userName(userName).build();

        historyRepository.save(history);
    }

    public void addPersonHistory(int id, String userName) {
        History history = History.builder()
                .idObj(id)
                .objectClass("Person")
                .time(LocalDateTime.now())
                .userName(userName).build();

        historyRepository.save(history);
    }
}
