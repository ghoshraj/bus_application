package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @Schema(example = "Raj", description = "Full name")
    @NotBlank(message = "name is required")
    private String name;

    @Schema(example = "raj@example.com", description = "Email address used for login")
    @Email(message = "email must be valid")
    @NotBlank(message = "email is required")
    private String email;

    @Schema(
            example = "StrongPassword@123",
            description = "Password (never returned in responses)",
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    @NotBlank(message = "password is required")
    private String password;

    @Schema(example = "9876543210")
    private String phoneNumber;

    @Schema(example = "MALE")
    private String gender;
}

