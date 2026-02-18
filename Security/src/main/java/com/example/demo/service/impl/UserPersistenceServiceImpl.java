package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.GlobalExceptionEnums;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPersistenceServiceImpl  {

    private final UserRepo userRepo;

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public User findByName(String name) {
        return userRepo.findByName(name)
                .orElseThrow(() -> new UserNotFoundException(GlobalExceptionEnums.USER_NOT_FOUND, name));
    }

    public User findByPhone(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber)
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElse(null);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(Integer id) {
        return userRepo.findById(id)
                .orElse(null);
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }
}
