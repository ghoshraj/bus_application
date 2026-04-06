package com.busapp.vehicle.service;

import com.busapp.vehicle.entity.Seats;
import com.busapp.vehicle.entity.Vehicle;

import java.util.List;

public interface SeatService {
    
    List<Seats> getSeatsByBusId(Integer busId);

    List<Seats> createSeatsForVehicle(Vehicle vehicle, int totalSeats);
}
