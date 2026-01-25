package com.busapp.route.exception;

import lombok.Getter;

@Getter
public class RouteAlreadyExists extends RuntimeException {

    private final int errorCode;

    public RouteAlreadyExists(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.ROUTE_ALREADY_EXIST.getErrorCode();
    }

    public RouteAlreadyExists(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
