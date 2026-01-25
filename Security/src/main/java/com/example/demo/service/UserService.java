package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.enums.Roles;

import java.util.List;
import java.util.Set;

public interface UserService {

    User addUser(User user);

    User findByName(String name);

    List<User> findAll();

    User assignRoles(Integer userId, Set<Roles> roles);

    User findById(Integer userId);
}
