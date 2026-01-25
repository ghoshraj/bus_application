package com.busapp.user.exception;

import lombok.Getter;

@Getter
public class TravelCompanyNotFound extends RuntimeException {

    private final int errorCode;

    public TravelCompanyNotFound(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.COMPANY_NOT_FOUND.getErrorCode();
    }

    public TravelCompanyNotFound(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}