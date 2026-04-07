package com.busapp.user.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final GlobalExceptionEnums error;
    private final int errorCode;

    public BusinessException(GlobalExceptionEnums error, Object... args) {
        super(error.getMessage(args));
        this.error = error;
        this.errorCode = error.getErrorCode();
    }
}
