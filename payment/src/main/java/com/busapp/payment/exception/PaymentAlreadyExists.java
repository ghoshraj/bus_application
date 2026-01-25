package com.busapp.payment.exception;

import lombok.Getter;

@Getter
public class PaymentAlreadyExists extends RuntimeException {

    private final int errorCode;

    public PaymentAlreadyExists(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.PAYMENT_ALREADY_EXISTS.getErrorCode();
    }

    public PaymentAlreadyExists(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
