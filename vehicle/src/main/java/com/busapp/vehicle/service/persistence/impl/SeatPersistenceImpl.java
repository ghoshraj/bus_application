package com.busapp.vehicle.service.persistence.impl;

import com.busapp.vehicle.entity.Seats;
import com.busapp.vehicle.entity.Vehicle;
import com.busapp.vehicle.repository.SeatRepository;
import com.busapp.vehicle.service.persistence.SeatPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatPersistenceImpl implements SeatPersistence {

    private final SeatRepository seatRepository;

    @Override
    public List<Seats> saveAll(List<Seats> seats) {
        return seatRepository.saveAll(seats);
    }

    @Override
    public List<Seats> getSeatsByVehicleId(Integer vehicleId) {
        // Since we have a Vehicle object reference, we need to query by vehicle.id
        // We'll use a custom query or get the vehicle first
        // For now, let's use findByVehicleId which should work with JPA
        return seatRepository.findByVehicleId(vehicleId);
    }
}
