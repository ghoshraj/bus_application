package com.busapp.booking.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookingRequest {

    @NotNull(message = "travelerId is required")
    private Integer travelerId;

    @NotNull(message = "scheduleId is required")
    private Integer scheduleId;

    @NotNull(message = "totalAmount is required")
    @Positive(message = "totalAmount must be positive")
    private Double totalAmount;
}
