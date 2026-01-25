package com.busapp.seatinventory.exception;

import lombok.Getter;

@Getter
public class SeatAlreadyLocked extends RuntimeException {

    private final int errorCode;

    public SeatAlreadyLocked(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.SEAT_ALREADY_LOCKED.getErrorCode();
    }

    public SeatAlreadyLocked(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
