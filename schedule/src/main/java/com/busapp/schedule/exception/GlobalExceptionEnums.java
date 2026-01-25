package com.busapp.schedule.exception;

import lombok.Getter;

@Getter
public enum GlobalExceptionEnums {

    GENERIC_ERROR(1000, "Generic error occurred"),

    SCHEDULE_ALREADY_EXIST(1500, "Schedule already exists"),
    SCHEDULE_NOT_FOUND(1501, "Schedule not found with id: %s"),
    DRIVER_NOT_FOUND(1502, "Driver not found for vehicleId: %s"),
    USER_SERVICE_ERROR(1503, "Failed to fetch operator from user service: %s"),

    VALIDATION_FAILED(1600, "Validation failed: %s");

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
