package com.example.demo.exception;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {

    private final int errorCode;

    public UserAlreadyExistsException(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.USER_ALREADY_EXISTS.getErrorCode();
    }

    public UserAlreadyExistsException(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}