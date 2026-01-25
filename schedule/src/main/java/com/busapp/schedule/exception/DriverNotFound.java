package com.busapp.schedule.exception;

import lombok.Getter;

@Getter
public class DriverNotFound extends RuntimeException {

    private final int errorCode;

    public DriverNotFound(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.DRIVER_NOT_FOUND.getErrorCode();
    }

    public DriverNotFound(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
