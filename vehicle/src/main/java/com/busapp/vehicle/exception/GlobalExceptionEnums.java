package com.busapp.vehicle.exception;

import lombok.Getter;

@Getter
public enum GlobalExceptionEnums {

    GENERIC_ERROR(1000, "Generic error occurred"),

    VEHICLE_ALREADY_EXIST(1200, "Vehicle already exists with vehicleNumber: %s"),
    VEHICLE_NOT_FOUND(1201, "Vehicle not found with id: %s"),

    SEAT_ALREADY_INITIALIZED(1300, "Seats already initialized for bus with id: %s"),
    SEAT_NOT_FOUND(1301, "Seat not found with id: %s"),

    VALIDATION_FAILED(1400, "Validation failed: %s");

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
