package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationRequest {
    @Schema(example = "raj@example.com", description = "Email/username")
    @NotBlank(message = "username is required")
    private String username;

    @Schema(
            example = "StrongPassword@123",
            description = "Password",
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    @NotBlank(message = "password is required")
    private String password;
}
