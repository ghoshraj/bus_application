package com.example.demo.service.security;

import com.example.demo.entity.User;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.service.impl.UserPersistenceServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.constant.SecurityConstants.ROLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserPersistenceServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        log.info("user role is : {}", user.getRoles());
        Set<String> roleNames = user.getRoles() != null
                ? user.getRoles().stream()
                .map(role -> role.name().replace(ROLE, ""))
                .collect(Collectors.toSet())
                : Set.of();
        String[] rolesArray = roleNames.toArray(new String[0]);
        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.name()))
                        .collect(Collectors.toList())
        );
    }
}
