package com.example.demo.service.impl;

import com.example.demo.entity.PasswordResetToken;
import com.example.demo.entity.User;
import com.example.demo.exception.GlobalExceptionEnums;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.repository.PasswordResetTokenRepo;
import com.example.demo.service.AuthService;
import com.example.demo.service.EmailService;
import com.example.demo.service.security.CustomUserDetailsService;
import com.example.demo.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.demo.constant.ApiConstants.LOGIN_SUCCESS;
import static com.example.demo.constant.ModelConstants.RESET_PASSWORD_LINK;
import static com.example.demo.constant.SecurityConstants.EXPIRY_MINUTES;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserPersistenceServiceImpl userPersistenceService;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordResetTokenRepo passwordResetTokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        String token = null;
        User user = userPersistenceService.findByEmail(request.getEmail());
        if (user != null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());
            token = jwtService.generateToken(userDetails);
        }
        return new AuthenticationResponse(token, LOGIN_SUCCESS);
    }

    @Override
    public void forgotPassword(String email) {
        User user = userPersistenceService.findByEmail(email);

        if (user == null)
            throw new UserAlreadyExistsException(GlobalExceptionEnums.USER_ALREADY_EXISTS, email);

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUserId(user.getId());
        resetToken.setToken(token);
        resetToken.setUsed(false);
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(EXPIRY_MINUTES));

        passwordResetTokenRepo.save(resetToken);

        String link = RESET_PASSWORD_LINK + token;
        emailService.sendResetEmail(user.getEmail(), link);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepo.findByToken(token)
                .orElseThrow(() -> new InvalidCredentialsException(GlobalExceptionEnums.TOKEN_INVALID));

        if (resetToken.isUsed())
            throw new InvalidCredentialsException(GlobalExceptionEnums.USED_TOKEN);

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now()))
            throw new InvalidCredentialsException(GlobalExceptionEnums.TOKEN_EXPIRED);

        User user = userPersistenceService.findById(resetToken.getUserId());

        if (user == null)
            throw new UserNotFoundException(GlobalExceptionEnums.USER_NOT_FOUND);

        user.setPassword(passwordEncoder.encode(newPassword));
        userPersistenceService.addUser(user);

        resetToken.setUsed(true);
        passwordResetTokenRepo.save(resetToken);
    }
}
