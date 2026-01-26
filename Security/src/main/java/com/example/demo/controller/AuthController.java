package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.model.ErrorResponse;
import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterResponse;
import com.example.demo.service.UserService;
import com.example.demo.service.security.CustomUserDetailsService;
import com.example.demo.service.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication endpoints")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register", description = "Creates a new user account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registered successfully",
                    content = @Content(schema = @Schema(implementation = RegisterResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation error / already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(userService.registerUser(request));

    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Authenticates user and returns a JWT token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token, "Login successful"));
    }
}