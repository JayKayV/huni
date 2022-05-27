package univer.program.service.impl;

//import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import univer.program.entity.User;
import univer.program.repository.UserRepository;
import univer.program.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserDetailsService userDetailsService,
                           AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public final List<User> findall() {
        return (List<User>)userRepository.findAll();
    }

    public final User save(User u) {
        return userRepository.save(u);
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
    public void login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);

        if (token.isAuthenticated())
            SecurityContextHolder.getContext().setAuthentication(token);
    }

    @Override
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }
}
