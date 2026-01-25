package com.busapp.vehicle.exception;

import lombok.Getter;

@Getter
public class VehicleNotFound extends RuntimeException {

    private final int errorCode;

    public VehicleNotFound(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.VEHICLE_NOT_FOUND.getErrorCode();
    }

    public VehicleNotFound(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
