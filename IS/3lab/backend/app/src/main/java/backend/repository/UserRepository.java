package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

import backend.model.Users;
import backend.model.UsersAdminStatus;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findAll();
    List<Users> findByAdminStatus(UsersAdminStatus status);
    Optional<Users> findByName(String username);
    boolean existsByName(String username);
}
