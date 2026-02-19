package com.example.demo.controller;

import com.example.demo.model.*;
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

import static com.example.demo.constant.ApiConstants.*;

@RestController
@RequestMapping(AUTH_BASE_PATH)
@RequiredArgsConstructor
@Tag(name = AUTH_TAG, description = AUTH_TAG_DESC)
public class AuthController {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping(REGISTER)
    @Operation(summary = REGISTER_SUMMARY, description = REGISTER_DESC)
    @ApiResponses({
            @ApiResponse(responseCode = OK_200, description = REGISTER_SUCCESS,
                    content = @Content(schema = @Schema(implementation = RegisterResponse.class))),
            @ApiResponse(responseCode = BAD_REQUEST_400, description = BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class)))
    })
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping(LOGIN)
    @Operation(summary = LOGIN_SUMMARY, description = LOGIN_DESC)
    @ApiResponses({
            @ApiResponse(responseCode = OK_200, description = LOGIN_SUCCESS,
                    content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = UNAUTHORIZED_401, description = UNAUTHORIZED,
                    content = @Content(schema = @Schema(implementation = UnauthorizedErrorResponse.class)))
    })
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(userService.login(request));
    }
}
