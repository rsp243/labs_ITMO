package backend.repository;

import backend.model.Color;
import backend.model.Coordinates;
import backend.model.Country;
import backend.model.Location;
import backend.model.Person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAll();
}
