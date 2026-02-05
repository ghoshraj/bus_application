package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.example.demo.constant.ModelConstants.*;

@Data
public class AuthenticationRequest {

    @Schema(example = USERNAME_EXAMPLE, description = USERNAME_DESC)
    @NotBlank(message = USERNAME_REQUIRED)
    private String username;

    @Schema(example = PASSWORD_EXAMPLE, description = PASSWORD_DESC,
            accessMode = Schema.AccessMode.WRITE_ONLY)
    @NotBlank(message = PASSWORD_REQUIRED)
    private String password;
}
