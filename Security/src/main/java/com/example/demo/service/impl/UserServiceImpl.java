package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.enums.ProfileStatus;
import com.example.demo.enums.Roles;
import com.example.demo.exception.GlobalExceptionEnums;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.messagebroker.model.TravellerProfileRequest;
import com.example.demo.messagebroker.producer.KafkaProducer;
import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterResponse;
import com.example.demo.model.UserResponse;
import com.example.demo.service.UserPersistenceService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.mapper.UserMapper.createdBy;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPersistenceService userPersistenceService;
    private final KafkaProducer kafkaProducer;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public RegisterResponse registerUser(RegisterRequest request) throws JsonProcessingException {
        log.info("Register request received for phone: {}", request.getPhoneNumber());
        if (userPersistenceService.findByPhone(request.getPhoneNumber()) != null)
            throw new UserAlreadyExistsException(GlobalExceptionEnums.USER_ALREADY_EXISTS, request.getPhoneNumber());

        User user = userMapper.toEntity(request);
        User savedUser = addUser(user);
        log.info("User created successfully with id: {}", savedUser.getId());
        TravellerProfileRequest event = TravellerProfileRequest.builder()
                .userId(savedUser.getId())
                .name(savedUser.getName())
                .phoneNumber(savedUser.getPhoneNumber())
                .gender(savedUser.getGender())
                .build();

        kafkaProducer.sendProfileCreationRequest(event);
        log.info("Kafka event sent for user profile creation: {}", savedUser.getId());
        return userMapper.toRegisterResponse(savedUser);
    }

    @Override
    public UserResponse assignRoles(Integer userId, Set<Roles> roles) {
        log.info("Assigning roles {} to user {}", roles, userId);
        User user = findById(userId);
        user.setRoles(roles);
        user.setUpdatedAt(Instant.now());
        user.setUpdatedBy("ADMIN");
        User updatedUser = userPersistenceService.updateUser(user);
        log.info("Roles updated successfully for user {}", userId);
        return userMapper.toUserResponse(updatedUser);
    }

    @Override
    public void updateProfileStatus(int user_id) {
        log.info("invoking profile update request for user :{}", user_id);
        User user = findById(user_id);
        user.setStatus(ProfileStatus.COMPLETED);
        user.setUpdatedAt(Instant.now());
        user.setUpdatedBy(createdBy);
        userPersistenceService.updateUser(user);
        log.info("User status updated to COMPLETED for user: {}", user_id);
    }

    @Override
    public User addUser(User user) {
        log.info("Adding new user with email: {}", user.getEmail());
        if (userPersistenceService.findByEmail(user.getEmail()) != null)
            throw new UserAlreadyExistsException(GlobalExceptionEnums.USER_ALREADY_EXISTS, user.getEmail());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(Roles.ROLE_USER));
        User savedUser = userPersistenceService.addUser(user);
        log.info("User saved successfully with id: {}", savedUser.getId());
        return savedUser;

    }

    @Override
    public User findByName(String name) {
        return userPersistenceService.findByName(name);
    }

    @Override
    public List<UserResponse> findAll() {
        List<UserResponse> users = userPersistenceService.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
        log.info("Total users fetched: {}", users.size());
        return users;
    }

    @Override
    public User findById(Integer userId) {
        User user = userPersistenceService.findById(userId);
        if (user == null) throw new UserNotFoundException(GlobalExceptionEnums.USER_NOT_FOUND, userId);
        return user;
    }
}
