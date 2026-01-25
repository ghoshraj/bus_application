package com.busapp.vehicle.service.impl;

import com.busapp.vehicle.entity.Seats;
import com.busapp.vehicle.service.SeatService;
import com.busapp.vehicle.service.VehicleService;
import com.busapp.vehicle.service.persistence.SeatPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatPersistence seatPersistence;
    private final VehicleService vehicleService;

    @Override
    public List<Seats> getSeatsByBusId(Integer busId) {
        // Verify vehicle exists
        vehicleService.getVehicleById(busId);
        
        return seatPersistence.getSeatsByVehicleId(busId);
    }
}
