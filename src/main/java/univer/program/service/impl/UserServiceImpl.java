package univer.program.service.impl;

//import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univer.program.entity.User;
import univer.program.repository.UserRepository;
import univer.program.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public final List<User> findall() {
        return (List<User>)userRepository.findAll();
    }

    public final void save(User u) {
        userRepository.save(u);
    }

    public final boolean deleteById(int id) {
        Optional<User> user = findById(id);
        if (user.isEmpty())
            return false;

        userRepository.delete(user.get());
        return true;
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

}
