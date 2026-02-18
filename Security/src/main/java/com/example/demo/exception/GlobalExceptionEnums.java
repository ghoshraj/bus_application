package com.example.demo.exception;

import lombok.Getter;

@Getter
public enum GlobalExceptionEnums {

    GENERIC_ERROR(1000, "Generic error occurred"),

    USER_ALREADY_EXISTS(1100, "User already exists with email/phone: %s"),
    USER_NOT_FOUND(1101, "User not found with email: %s"),

    INVALID_CREDENTIALS(1200, "Invalid credentials for user: %s"),
    TOKEN_EXPIRED(1201, "JWT token has expired"),
    TOKEN_INVALID(1202, "Invalid JWT token"),

    VALIDATION_FAILED(1300, "Validation failed: %s"),
    ACCESS_DENIED(1400, "Access denied for role: %s");

    private final int errorCode;
    private final String message;

    GlobalExceptionEnums(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getMessage(Object... args) {
        try {
            return String.format(this.message, args);
        } catch (Exception e) {
            return this.message;
        }
    }
}
