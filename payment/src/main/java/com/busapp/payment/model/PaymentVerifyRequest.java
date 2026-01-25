package com.busapp.payment.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaymentVerifyRequest {

    @NotBlank(message = "transactionId is required")
    private String transactionId;
}
