package com.example.demo.service.security;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserPersistenceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.constant.SecurityConstants.ROLE;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserPersistenceServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(username);

        Set<String> roleNames = user.getRoles() != null
                ? user.getRoles().stream()
                .map(role -> role.name().replace(ROLE, ""))
                .collect(Collectors.toSet())
                : Set.of();
        String[] rolesArray = roleNames.toArray(new String[0]);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getName())
                .password(user.getPassword())
                .roles(rolesArray)
                .build();
    }
}
