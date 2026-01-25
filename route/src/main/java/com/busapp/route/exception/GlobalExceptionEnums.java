package com.busapp.route.exception;

import lombok.Getter;

@Getter
public enum GlobalExceptionEnums {

    GENERIC_ERROR(1000, "Generic error occurred"),

    ROUTE_ALREADY_EXIST(1400, "Route already exists from %s to %s"),
    ROUTE_NOT_FOUND(1401, "Route not found with id: %s"),
    ROUTE_NOT_FOUND_BY_SOURCE_DEST(1402, "Route not found from %s to %s"),

    VALIDATION_FAILED(1500, "Validation failed: %s");

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
