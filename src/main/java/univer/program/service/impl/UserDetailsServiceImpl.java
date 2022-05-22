package univer.program.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import univer.program.entity.User;
import univer.program.entity.UserRole;
import univer.program.repository.UserRoleRepository;
//import univer.program.service.UserService;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserRole> optionalRole = userRoleRepository.findByUsername(username);

        if (optionalRole.isPresent()) {
            UserRole userRole = optionalRole.get();
            Set<GrantedAuthority> authorities = new HashSet<>();

            if (Objects.equals(userRole.getRole(),"admin"))
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            else
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new org.springframework.security.core.userdetails.User(userRole.getUsername(), userRole.getPassword(), authorities);
        } else
            throw new UsernameNotFoundException(username + " not found!");
    }
}
