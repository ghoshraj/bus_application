package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.example.demo.constant.ModelConstants.*;

@Data
public class RegisterRequest {

    @Schema(example = USERNAME_EXAMPLE, description = "Full name")
    @NotBlank(message = NAME_REQUIRED)
    private String name;

    @Schema(example = USER_EMAIL_EXAMPLE, description = "Email address used for login")
    @Email(message = EMAIL_INVALID)
    @NotBlank(message = EMAIL_REQUIRED)
    private String email;

    @Schema(example = PASSWORD_EXAMPLE, description = "Password (never returned in responses)",
            accessMode = Schema.AccessMode.WRITE_ONLY)
    @NotBlank(message = PASSWORD_REQUIRED)
    private String password;

    @Schema(example = USER_PHONE_EXAMPLE)
    private String phoneNumber;

    @Schema(example = USER_GENDER_EXAMPLE)
    private String gender;
}

