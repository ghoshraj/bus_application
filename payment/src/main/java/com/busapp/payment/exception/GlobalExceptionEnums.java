package com.busapp.payment.exception;

import lombok.Getter;

@Getter
public enum GlobalExceptionEnums {

    GENERIC_ERROR(1000, "Generic error occurred"),

    PAYMENT_ALREADY_EXISTS(2100, "Payment already exists for bookingId: %s"),
    PAYMENT_NOT_FOUND(2101, "Payment not found with id: %s"),
    PAYMENT_NOT_FOUND_BY_TRANSACTION_ID(2102, "Payment not found with transactionId: %s"),
    PAYMENT_NOT_PENDING(2103, "Payment is not in PENDING status"),
    PAYMENT_NOT_SUCCESSFUL(2104, "Only successful payments can be refunded"),

    VALIDATION_FAILED(2200, "Validation failed: %s");

    private final int errorCode;
    private final String message;

    GlobalExceptionEnums(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getMessage(Object... args) {
        try {
            return String.format(this.message, args);
        } catch (Exception e) {
            return this.message;
        }
    }
}
