package com.busapp.user.exception;

import lombok.Getter;

@Getter
public class TravelCompanyAlreadyExist extends RuntimeException {

    private final int errorCode;

    public TravelCompanyAlreadyExist(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.COMPANY_ALREADY_EXIST.getErrorCode();
    }

    public TravelCompanyAlreadyExist(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}