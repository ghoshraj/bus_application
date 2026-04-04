package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.enums.ProfileStatus;

import java.util.List;

public interface UserPersistenceService {

    User addUser(User user);

    User updateUser(User user);

    User findById(Integer id);

    List<User> findAll();

    User findByEmail(String email);

    User findByPhone(String phoneNumber);

    User findByName(String name);

    List<User> findByStatus(ProfileStatus status);
}
