package com.busapp.payment.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RefundRequest {

    @NotNull(message = "paymentId is required")
    private Integer paymentId;
}
