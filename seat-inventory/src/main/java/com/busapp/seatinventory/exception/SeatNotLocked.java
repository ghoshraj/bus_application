package com.busapp.seatinventory.exception;

import lombok.Getter;

@Getter
public class SeatNotLocked extends RuntimeException {

    private final int errorCode;

    public SeatNotLocked(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.SEAT_NOT_LOCKED.getErrorCode();
    }

    public SeatNotLocked(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
