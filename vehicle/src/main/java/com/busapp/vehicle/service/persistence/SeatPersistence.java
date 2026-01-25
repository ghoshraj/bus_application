package com.busapp.vehicle.service.persistence;

import com.busapp.vehicle.entity.Seats;

import java.util.List;

public interface SeatPersistence {

    List<Seats> saveAll(List<Seats> seats);
    
    List<Seats> getSeatsByVehicleId(Integer vehicleId);
}
