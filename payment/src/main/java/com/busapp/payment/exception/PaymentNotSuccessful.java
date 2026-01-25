package com.busapp.payment.exception;

import lombok.Getter;

@Getter
public class PaymentNotSuccessful extends RuntimeException {

    private final int errorCode;

    public PaymentNotSuccessful(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.PAYMENT_NOT_SUCCESSFUL.getErrorCode();
    }

    public PaymentNotSuccessful(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
