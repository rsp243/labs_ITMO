package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import backend.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAll();
}
