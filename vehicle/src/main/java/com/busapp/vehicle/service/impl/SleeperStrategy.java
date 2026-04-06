package com.busapp.vehicle.service.impl;

import com.busapp.vehicle.entity.Seats;
import com.busapp.vehicle.enums.SeatType;
import com.busapp.vehicle.service.SeatStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("SLEEPER")
@RequiredArgsConstructor
public class SleeperStrategy implements SeatStrategy {

    @Override
    public void applySeatDetails(Seats seat, int index, int totalSeats) {
        if (index % 2 == 0) {
            seat.setSeatType(SeatType.UPPER_SLEEPER);
            seat.setPrice(800.0);
        } else {
            seat.setSeatType(SeatType.LOWER_SLEEPER);
            seat.setPrice(900.0);
        }
    }
}
