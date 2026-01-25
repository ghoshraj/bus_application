package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.enums.Roles;
import com.example.demo.exception.GlobalExceptionEnums;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserPersistenceServiceImpl userPersistenceService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {

        if (userPersistenceService.getUserByEmail(user.getEmail()) == null) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setRoles(Set.of(Roles.ROLE_USER));
            return userPersistenceService.addUser(user);
        } else {
            throw new UserAlreadyExistsException(
                    GlobalExceptionEnums.USER_ALREADY_EXISTS,
                    user.getEmail()
            );
        }
    }

    @Override
    public User findByName(String name) {
        return userPersistenceService.findByName(name);
    }

    @Override
    public List<User> findAll() {
        return userPersistenceService.findAll();
    }

    @Override
    @Transactional
    public User assignRoles(Integer userId, Set<Roles> roles) {
        User user = findById(userId);
        if (user == null) {
            throw new UserNotFoundException(
                    GlobalExceptionEnums.USER_NOT_FOUND,
                    "User with id: " + userId
            );
        }
        user.setRoles(roles);
        return userPersistenceService.updateUser(user);
    }

    @Override
    public User findById(Integer userId) {
        return userPersistenceService.findById(userId);
    }
}
