package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Coordinates;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
    List<Coordinates> findAll();
    void deleteById(int id);
    List<Coordinates> findByXAndY(Long x, Double y);
}
