package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.enums.Gender;
import com.example.demo.messagebroker.model.TravellerProfileRequest;
import com.example.demo.messagebroker.producer.KafkaProducer;
import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterResponse;
import com.example.demo.model.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final KafkaProducer kafkaProducer;

    public User toEntity(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setGender(Gender.valueOf(request.getGender().toUpperCase()));
        return user;
    }

    public RegisterResponse toRegisterResponse(User user) throws JsonProcessingException {
        RegisterResponse response = new RegisterResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        toResponse(user);
        return response;
    }

    private void toResponse(User user) throws JsonProcessingException {
        TravellerProfileRequest travellerProfileRequest =
                TravellerProfileRequest.builder().userId(user.getId())
                        .name(user.getName())
                        .phoneNumber(user.getPhoneNumber())
                        .gender(user.getGender()).build();
        kafkaProducer.sendRequest(travellerProfileRequest);
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

