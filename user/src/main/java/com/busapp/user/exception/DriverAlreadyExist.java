package com.busapp.user.exception;

import lombok.Getter;

@Getter
public class DriverAlreadyExist extends RuntimeException {

    private final int errorCode;

    public DriverAlreadyExist(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.DRIVER_ALREADY_EXIST.getErrorCode();
    }

    public DriverAlreadyExist(GlobalExceptionEnums exceptionEnums, Object... args) {
        super(exceptionEnums.getMessage(args));
        this.errorCode = exceptionEnums.getErrorCode();
    }
}
