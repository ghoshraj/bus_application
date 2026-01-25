package com.busapp.route.exception;

import lombok.Getter;

@Getter
public class RouteNotFound extends RuntimeException {

    private final int errorCode;

    public RouteNotFound(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.ROUTE_NOT_FOUND.getErrorCode();
    }

    public RouteNotFound(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
