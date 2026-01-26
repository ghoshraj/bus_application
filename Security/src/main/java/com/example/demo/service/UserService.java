package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.enums.Roles;
import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterResponse;
import com.example.demo.model.UserResponse;

import java.util.List;
import java.util.Set;

public interface UserService {

    User addUser(User user);

    User findByName(String name);

    List<UserResponse> findAll();

    User findById(Integer userId);

    RegisterResponse registerUser(RegisterRequest request);

    UserResponse assignRoles(Integer userId, Set<Roles> roles);
}
