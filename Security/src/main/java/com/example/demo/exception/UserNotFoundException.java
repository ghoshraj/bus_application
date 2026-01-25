package com.example.demo.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private final int errorCode;

    public UserNotFoundException(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.USER_NOT_FOUND.getErrorCode();
    }

    public UserNotFoundException(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}