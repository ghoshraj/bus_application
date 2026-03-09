package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final UserService userService;
    private final AuthService authService;

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
            @ApiResponse(responseCode = BAD_REQUEST_400, description = BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class)))
    })
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/forgot-password")
    @Operation(summary = "forgot-password", description = "user is try to forgot password")
    @ApiResponses({
            @ApiResponse(responseCode = OK_200, description = "link send to register mail"),
            @ApiResponse(responseCode = BAD_REQUEST_400, description = BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class)))
    })
    public ResponseEntity<Void> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
        authService.forgotPassword(forgotPasswordRequest.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    @Operation(summary = "forgot-password", description = "user is try to update the password")
    @ApiResponses({
            @ApiResponse(responseCode = OK_200, description = "Password reset successful"),
            @ApiResponse(responseCode = BAD_REQUEST_400, description = BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class)))
    })
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        if (!resetPasswordRequest.getNewPassword()
                .equals(resetPasswordRequest.getConfirmPassword())) {
            return ResponseEntity.badRequest()
                    .body("Passwords do not match");
        }

        authService.resetPassword(resetPasswordRequest.getToken(), resetPasswordRequest.getNewPassword());

        return ResponseEntity.ok("Password reset successful");
    }
}
