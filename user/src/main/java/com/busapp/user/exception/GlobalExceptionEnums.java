package com.busapp.user.exception;

import lombok.Getter;

@Getter
public enum GlobalExceptionEnums {

    GENERIC_ERROR(1000, "Generic error occurred"),

    COMPANY_ALREADY_EXIST(1100, "Company already exists with registrationId: %s"),
    DRIVER_ALREADY_EXIST(1101, "Driver already register with this adharNumber: %s"),
    COMPANY_NOT_FOUND(1102, "Company not found with id/registrationNumber: %s"),
    USER_NOT_FOUND(1103, "User not found with id: %s"),
    COMPANY_RESOURCE_REQUIREMENT_NOT_MET(1104, "Company must have minimum 2 vehicles and 6 drivers, but found %s drivers"),
    COMPANY_DRIVER_REQUIREMENT_NOT_MET(1105, "Company must have minimum 3 driver required per vehicle but found %s drivers"),

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
