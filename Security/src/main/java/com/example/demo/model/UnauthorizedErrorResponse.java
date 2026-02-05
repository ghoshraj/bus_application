package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.demo.constant.ApiConstants.UNAUTHORIZED;
import static com.example.demo.constant.ApiConstants.UNAUTHORIZED_401;
import static com.example.demo.constant.ModelConstants.TIMESTAMP_EXAMPLE;
import static com.example.demo.constant.ModelConstants.UNAUTHORIZED_DESC;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = UNAUTHORIZED_DESC)
public class UnauthorizedErrorResponse {

    @Schema(example = UNAUTHORIZED_401)
    private int status;

    @Schema(example = UNAUTHORIZED)
    private String message;

    @Schema(example = UNAUTHORIZED_401)
    private int errorCode;

    @Schema(example = TIMESTAMP_EXAMPLE)
    private String timestamp;
}

