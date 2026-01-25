package com.example.demo.exception;

import lombok.Getter;

@Getter
public class InvalidCredentialsException extends RuntimeException {

    private final int errorCode;

    public InvalidCredentialsException(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.INVALID_CREDENTIALS.getErrorCode();
    }

    public InvalidCredentialsException(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}