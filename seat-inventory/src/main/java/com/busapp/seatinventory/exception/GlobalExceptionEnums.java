package com.busapp.seatinventory.exception;

import lombok.Getter;

@Getter
public enum GlobalExceptionEnums {

    GENERIC_ERROR(1000, "Generic error occurred"),

    SEAT_NOT_FOUND(1700, "Seat not found for busId: %s and seatNumber: %s"),
    SEAT_ALREADY_LOCKED(1701, "Seat already locked for busId: %s and seatNumber: %s"),
    SEAT_NOT_LOCKED(1702, "Seat is not locked for busId: %s and seatNumber: %s"),
    USER_SERVICE_ERROR(1703, "Failed to fetch schedule from schedule service: %s"),
    SCHEDULE_NOT_FOUND(1704, "Schedule not found or busId not available for scheduleId: %s"),

    VALIDATION_FAILED(1800, "Validation failed: %s");

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
