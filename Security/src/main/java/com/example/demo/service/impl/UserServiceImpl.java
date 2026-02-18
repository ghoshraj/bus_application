package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.enums.Roles;
import com.example.demo.exception.GlobalExceptionEnums;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterResponse;
import com.example.demo.model.UserResponse;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserPersistenceServiceImpl userPersistenceService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
    public RegisterResponse registerUser(RegisterRequest request) {

        User savedUser = null;
        if (userPersistenceService.findByPhone(request.getPhoneNumber()) == null) {
            User user = userMapper.toEntity(request);
            savedUser = addUser(user);
        }
        else {
            throw new UserAlreadyExistsException(GlobalExceptionEnums.USER_ALREADY_EXISTS, request.getPhoneNumber());
        }
        return userMapper.toRegisterResponse(savedUser);
    }

    @Override
    public UserResponse assignRoles(Integer userId, Set<Roles> roles) {

        User user = findById(userId);
        if (user == null) {
            throw new UserNotFoundException(GlobalExceptionEnums.USER_NOT_FOUND, "User with id: " + userId);
        }
        user.setRoles(roles);

        User updatedUser = userPersistenceService.updateUser(user);

        return userMapper.toUserResponse(updatedUser);
    }

    @Override
    public User addUser(User user) {

        if (userPersistenceService.getUserByEmail(user.getEmail()) == null) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setRoles(Set.of(Roles.ROLE_USER));
            return userPersistenceService.addUser(user);
        } else {
            throw new UserAlreadyExistsException(GlobalExceptionEnums.USER_ALREADY_EXISTS, user.getEmail());
        }
    }

    @Override
    public User findByName(String name) {
        return userPersistenceService.findByName(name);
    }

    @Override
    public List<UserResponse> findAll() {
        return userPersistenceService.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public User findById(Integer userId) {
        return userPersistenceService.findById(userId);
    }
}
