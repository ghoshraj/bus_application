package com.busapp.seatinventory.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UnlockRequest {

    @NotNull(message = "busId is required")
    private Integer busId;

    @NotNull(message = "seatNumber is required")
    private String seatNumber;
}
