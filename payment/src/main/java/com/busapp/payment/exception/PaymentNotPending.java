package com.busapp.payment.exception;

import lombok.Getter;

@Getter
public class PaymentNotPending extends RuntimeException {

    private final int errorCode;

    public PaymentNotPending(String message) {
        super(message);
        this.errorCode = GlobalExceptionEnums.PAYMENT_NOT_PENDING.getErrorCode();
    }

    public PaymentNotPending(GlobalExceptionEnums exceptionEnum, Object... args) {
        super(exceptionEnum.getMessage(args));
        this.errorCode = exceptionEnum.getErrorCode();
    }
}
