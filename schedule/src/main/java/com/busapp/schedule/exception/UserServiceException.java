package com.busapp.schedule.exception;

import lombok.Getter;

@Getter
public class UserServiceException extends RuntimeException {

    private final int errorCode;

    public UserServiceException(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.USER_SERVICE_ERROR.getErrorCode();
    }

    public UserServiceException(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
