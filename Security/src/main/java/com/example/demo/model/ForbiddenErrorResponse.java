package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.demo.constant.ApiConstants.FORBIDDEN;
import static com.example.demo.constant.ApiConstants.FORBIDDEN_403;
import static com.example.demo.constant.ModelConstants.FORBIDDEN_DESC;
import static com.example.demo.constant.ModelConstants.TIMESTAMP_EXAMPLE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = FORBIDDEN_DESC)
public class ForbiddenErrorResponse {

    @Schema(example = FORBIDDEN_403)
    private int status;

    @Schema(example = FORBIDDEN)
    private String message;

    @Schema(example = FORBIDDEN_403)
    private int errorCode;

    @Schema(example = TIMESTAMP_EXAMPLE)
    private String timestamp;
}

