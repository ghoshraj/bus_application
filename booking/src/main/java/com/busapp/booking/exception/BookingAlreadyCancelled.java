package com.busapp.booking.exception;

import lombok.Getter;

@Getter
public class BookingAlreadyCancelled extends RuntimeException {

    private final int errorCode;

    public BookingAlreadyCancelled(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.BOOKING_ALREADY_CANCELLED.getErrorCode();
    }

    public BookingAlreadyCancelled(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
