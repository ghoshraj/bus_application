package com.busapp.seatinventory.exception;

import lombok.Getter;

@Getter
public class ScheduleNotFound extends RuntimeException {

    private final int errorCode;

    public ScheduleNotFound(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.SCHEDULE_NOT_FOUND.getErrorCode();
    }

    public ScheduleNotFound(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
