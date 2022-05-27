package univer.program.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import univer.program.entity.User;
import univer.program.repository.UserRepository;
import univer.program.service.UserService;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRoleRepository) {
        this.userRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            User u = user.get();
            String role = u.getRole();
            Set<GrantedAuthority> authorities = new HashSet<>();

            if (Objects.equals(u.getRole(),"admin"))
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            else
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), authorities);
        } else
            throw new UsernameNotFoundException(username + " not found!");
    }
}
