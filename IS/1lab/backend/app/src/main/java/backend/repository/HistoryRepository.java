package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.DTO.HistoryCreatedDTO;
import backend.model.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<HistoryCreatedDTO> findAllByObjectClassAndId(String objectClass, int id);
}
