package com.busapp.payment.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotNull(message = "bookingId is required")
    private Integer bookingId;

    @NotNull(message = "paymentMethod is required")
    private String paymentMethod;

    @NotNull(message = "amount is required")
    @Positive(message = "amount must be positive")
    private Double amount;
}
