package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.demo.constant.ApiConstants.BAD_REQUEST;
import static com.example.demo.constant.ApiConstants.BAD_REQUEST_400;
import static com.example.demo.constant.ModelConstants.BAD_REQUEST_DESC;
import static com.example.demo.constant.ModelConstants.TIMESTAMP_EXAMPLE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = BAD_REQUEST_DESC)
public class BadRequestErrorResponse {

    @Schema(example = BAD_REQUEST_400)
    private int status;

    @Schema(example = BAD_REQUEST)
    private String message;

    @Schema(example = BAD_REQUEST_400)
    private int errorCode;

    @Schema(example = TIMESTAMP_EXAMPLE)
    private String timestamp;
}

