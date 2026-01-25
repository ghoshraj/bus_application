package com.busapp.payment.exception;

import lombok.Getter;

@Getter
public class PaymentNotFound extends RuntimeException {

    private final int errorCode;

    public PaymentNotFound(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.PAYMENT_NOT_FOUND.getErrorCode();
    }

    public PaymentNotFound(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
