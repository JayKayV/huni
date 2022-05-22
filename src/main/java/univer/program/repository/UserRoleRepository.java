package univer.program.repository;

import org.springframework.data.repository.CrudRepository;
import univer.program.entity.UserRole;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    Optional<UserRole> findByUsername(String username);
}
