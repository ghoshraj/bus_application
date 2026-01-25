package com.busapp.seatinventory.exception;

import lombok.Getter;

@Getter
public class SeatNotFound extends RuntimeException {

    private final int errorCode;

    public SeatNotFound(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.SEAT_NOT_FOUND.getErrorCode();
    }

    public SeatNotFound(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
