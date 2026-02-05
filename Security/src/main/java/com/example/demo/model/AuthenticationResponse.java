package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.example.demo.constant.ModelConstants.LOGIN_MESSAGE_EXAMPLE;
import static com.example.demo.constant.ModelConstants.TOKEN_EXAMPLE;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    @Schema(example = TOKEN_EXAMPLE)
    private String token;
    @Schema(example = LOGIN_MESSAGE_EXAMPLE)
    private String message;

}
