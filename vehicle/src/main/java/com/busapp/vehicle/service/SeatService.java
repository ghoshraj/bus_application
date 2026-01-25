package com.busapp.vehicle.service;

import com.busapp.vehicle.entity.Seats;

import java.util.List;

public interface SeatService {
    
    List<Seats> getSeatsByBusId(Integer busId);
}
