package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.enums.Roles;
import com.example.demo.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Set;

public interface UserService {

    User addUser(User user);

    User findByName(String name);

    List<UserResponse> findAll();

    User findById(Integer userId);

    RegisterResponse registerUser(RegisterRequest request) throws JsonProcessingException;

    UserResponse assignRoles(Integer userId, Set<Roles> roles);

    void updateProfileStatus(int user_id);
}
