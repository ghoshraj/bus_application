package com.busapp.booking.exception;

import lombok.Getter;

@Getter
public enum GlobalExceptionEnums {

    GENERIC_ERROR(1000, "Generic error occurred"),

    BOOKING_ALREADY_EXIST(1900, "Booking already exists"),
    BOOKING_NOT_FOUND(1901, "Booking not found with id: %s"),
    BOOKING_ALREADY_CANCELLED(1902, "Booking is already cancelled"),

    VALIDATION_FAILED(2000, "Validation failed: %s");

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
