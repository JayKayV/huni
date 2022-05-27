package univer.program.service;

import univer.program.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    List<User> findall();

    boolean deleteById(int id);

    Optional<User> findById(int id);

    void login(String username, String password);
}
