package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.enums.ProfileStatus;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPersistenceServiceImpl implements UserPersistenceService {

    private final UserRepo userRepo;

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User findByName(String name) {
        return userRepo.findByName(name)
                .orElse(null);
    }

    @Override
    public List<User> findByStatus(ProfileStatus status) {
        List<User> user =  userRepo.findByStatus(status);
        if (user != null) return user;
        return List.of();
    }

    @Override
    public User findByPhone(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber)
                .orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepo.findById(id)
                .orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }
}
