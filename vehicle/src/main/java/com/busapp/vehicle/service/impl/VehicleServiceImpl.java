package com.busapp.vehicle.service.impl;

import com.busapp.vehicle.entity.Seats;
import com.busapp.vehicle.entity.Vehicle;
import com.busapp.vehicle.enums.Status;
import com.busapp.vehicle.exception.GlobalExceptionEnums;
import com.busapp.vehicle.exception.VehicleAlreadyExists;
import com.busapp.vehicle.exception.VehicleNotFound;
import com.busapp.vehicle.service.VehicleService;
import com.busapp.vehicle.service.persistence.SeatPersistence;
import com.busapp.vehicle.service.persistence.VehiclePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehiclePersistence vehiclePersistence;
    private final SeatPersistence seatPersistence;

    @Override
    @Transactional
    public Vehicle applyVehicle(Vehicle vehicle) {
        String vehicleNumber = vehicle.getVehicleNumber();
        Optional<Vehicle> existingVehicle = vehiclePersistence.getVehicleByNumber(vehicleNumber);
        if (existingVehicle.isEmpty()) {
            vehicle.setStatus(Status.PENDING);
            vehicle.setCreatedAt(Instant.now());
            vehicle.setCreatedBy("");
            vehicle.setCompanyId(2);
            
            // Save vehicle first
            Vehicle savedVehicle = vehiclePersistence.applyVehicle(vehicle);
            
            // Automatically create seats if totalSeats is provided
            if (vehicle.getTotalSeats() != null && !vehicle.getTotalSeats().isEmpty()) {
                try {
                    int totalSeats = Integer.parseInt(vehicle.getTotalSeats());
                    List<Seats> seats = createSeatsForVehicle(savedVehicle, totalSeats);
                    seatPersistence.saveAll(seats);
                } catch (NumberFormatException e) {
                    // If totalSeats is not a valid number, skip seat creation
                }
            }
            
            return savedVehicle;
        } else {
            throw new VehicleAlreadyExists(GlobalExceptionEnums.VEHICLE_ALREADY_EXIST, vehicleNumber);
        }
    }
    
    private List<Seats> createSeatsForVehicle(Vehicle vehicle, int totalSeats) {
        List<Seats> seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            Seats seat = new Seats();
            seat.setVehicle(vehicle);
            seat.setSeatNumber(String.valueOf(i));
            seat.setSeatType("REGULAR");
            seat.setStatus(Status.ACTIVE);
            seat.setCreatedAt(Instant.now());
            seat.setCreatedBy("");
            seats.add(seat);
        }
        return seats;
    }

    @Override
    public Vehicle getVehicleById(Integer id) {
        return vehiclePersistence.getVehicleById(id)
                .orElseThrow(() -> new VehicleNotFound(GlobalExceptionEnums.VEHICLE_NOT_FOUND, id));
    }

    @Override
    public List<Vehicle> getVehiclesByCompanyId(int companyId) {
        return vehiclePersistence.getVehiclesByCompanyId(companyId);
    }
}
