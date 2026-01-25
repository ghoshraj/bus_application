package com.busapp.booking.exception;

import lombok.Getter;

@Getter
public class BookingNotFound extends RuntimeException {

    private final int errorCode;

    public BookingNotFound(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.BOOKING_NOT_FOUND.getErrorCode();
    }

    public BookingNotFound(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
