package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.enums.Gender;
import com.example.demo.enums.ProfileStatus;
import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterResponse;
import com.example.demo.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public static final String createdBy = "system_user";

    public User toEntity(RegisterRequest request) {
        User user = new User();
        user.setCreatedBy(createdBy);
        user.setCreatedAt(Instant.now());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setGender(Gender.valueOf(request.getGender().toUpperCase()));
        user.setStatus(ProfileStatus.PENDING);
        return user;
    }

    public RegisterResponse toRegisterResponse(User user) {
        RegisterResponse response = new RegisterResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getGender(),
                user.getRoles()
        );
    }
}

