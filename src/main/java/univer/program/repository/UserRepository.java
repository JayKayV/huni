package univer.program.repository;

import org.springframework.data.repository.CrudRepository;
import univer.program.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
}
