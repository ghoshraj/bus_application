package com.busapp.vehicle.exception;

import lombok.Getter;

@Getter
public class SeatAlreadyInitialized extends RuntimeException {

    private final int errorCode;

    public SeatAlreadyInitialized(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.SEAT_ALREADY_INITIALIZED.getErrorCode();
    }

    public SeatAlreadyInitialized(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
