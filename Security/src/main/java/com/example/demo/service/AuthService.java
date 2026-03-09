package com.example.demo.service;

import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse login(AuthenticationRequest request);

    void forgotPassword(String email);

    void resetPassword(String token, String newPassword);
}
