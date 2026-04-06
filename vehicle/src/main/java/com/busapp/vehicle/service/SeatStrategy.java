package com.busapp.vehicle.service;

import com.busapp.vehicle.entity.Seats;

public interface SeatStrategy {

    void applySeatDetails(Seats seat, int index, int totalSeats);
}
