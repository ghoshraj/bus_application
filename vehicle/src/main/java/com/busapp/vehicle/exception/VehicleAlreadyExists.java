package com.busapp.vehicle.exception;

import lombok.Getter;

@Getter
public class VehicleAlreadyExists extends RuntimeException {

    private final int errorCode;

    public VehicleAlreadyExists(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.VEHICLE_ALREADY_EXIST.getErrorCode();
    }

    public VehicleAlreadyExists(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
