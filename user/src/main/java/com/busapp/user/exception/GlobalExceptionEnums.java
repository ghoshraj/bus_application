package com.busapp.user.exception;

import lombok.Getter;

@Getter
public enum GlobalExceptionEnums {

    GENERIC_ERROR(1000, "Generic error occurred"),

    COMPANY_ALREADY_EXIST(1100, "Company already exists with registrationId: %s"),
    COMPANY_NOT_FOUND(1101, "User not found with email: %s"),
    USER_NOT_FOUND(1102, "User not found with id: %s"),

    VALIDATION_FAILED(1300, "Validation failed: %s");

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
