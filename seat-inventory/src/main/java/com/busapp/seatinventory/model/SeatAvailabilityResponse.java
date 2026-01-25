package com.busapp.seatinventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatAvailabilityResponse {

    private Integer busId;
    private List<String> availableSeats;
    private List<String> lockedSeats;
    private Integer totalSeats;
    private Integer availableCount;
    private Integer lockedCount;
}
