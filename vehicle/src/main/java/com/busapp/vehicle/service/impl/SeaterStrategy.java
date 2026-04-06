package com.busapp.vehicle.service.impl;

import com.busapp.vehicle.entity.Seats;
import com.busapp.vehicle.enums.SeatType;
import com.busapp.vehicle.service.SeatStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("SEATER")
@RequiredArgsConstructor
public class SeaterStrategy implements SeatStrategy {

    @Override
    public void applySeatDetails(Seats seat, int index, int totalSeats) {
        seat.setSeatType(SeatType.SEATER);
        seat.setPrice(500.0);
    }
}
