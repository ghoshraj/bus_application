package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static com.example.demo.constant.ModelConstants.*;

@Data
public class RegisterResponse {

    @Schema(example = USER_ID_EXAMPLE)
    private int id;
    @Schema(example = USERNAME_EXAMPLE)
    private String name;
    @Schema(example = USER_EMAIL_EXAMPLE)
    private String email;

}
