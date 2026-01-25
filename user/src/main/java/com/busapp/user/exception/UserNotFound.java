package com.busapp.user.exception;

import lombok.Getter;

@Getter
public class UserNotFound extends RuntimeException {

    private final int errorCode;

    public UserNotFound(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.USER_NOT_FOUND.getErrorCode();
    }

    public UserNotFound(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
