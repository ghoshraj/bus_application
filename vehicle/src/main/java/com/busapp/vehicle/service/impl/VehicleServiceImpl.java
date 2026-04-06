package com.busapp.vehicle.service.impl;

import com.busapp.vehicle.entity.Seats;
import com.busapp.vehicle.entity.Vehicle;
import com.busapp.vehicle.enums.BookingStatus;
import com.busapp.vehicle.exception.GlobalExceptionEnums;
import com.busapp.vehicle.exception.VehicleAlreadyExists;
import com.busapp.vehicle.exception.VehicleNotFound;
import com.busapp.vehicle.model.VehicleRequest;
import com.busapp.vehicle.model.VehicleResponse;
import com.busapp.vehicle.service.SeatService;
import com.busapp.vehicle.service.VehicleService;
import com.busapp.vehicle.service.persistence.SeatPersistence;
import com.busapp.vehicle.service.persistence.VehiclePersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehiclePersistence vehiclePersistence;
    private final SeatPersistence seatPersistence;
    private final SeatService seatService;

    @Override
    @Transactional
    public VehicleResponse applyVehicle(VehicleRequest vehicle) {
        log.info("Starting vehicle creation for vehicleNumber={}", vehicle.getVehicleNumber());
        String vehicleNumber = vehicle.getVehicleNumber();
        Optional<Vehicle> existingVehicle = vehiclePersistence.getVehicleByNumber(vehicleNumber);
        if (existingVehicle.isPresent()) {
            log.error("Vehicle already exists with vehicleNumber={}", vehicleNumber);
            throw new VehicleAlreadyExists(GlobalExceptionEnums.VEHICLE_ALREADY_EXIST, vehicleNumber);
        }
        Vehicle bus = new Vehicle();
        bus.setBookingStatus(BookingStatus.AVAILABLE);
        bus.setCreatedAt(Instant.now());
        bus.setCreatedBy("SYSTEM");
        bus.setCompanyId(vehicle.getCompanyId());
        bus.setBusType(vehicle.getBusType());
        bus.setVehicleColor(vehicle.getVehicleColor());
        bus.setVehicleModel(vehicle.getVehicleModel());
        bus.setVehicleImage(vehicle.getVehicleImage());
        bus.setVehicleNumber(vehicle.getVehicleNumber());
        bus.setTotalSeats(vehicle.getTotalSeats());
        log.debug("Saving vehicle with details: {}", bus);
        Vehicle savedVehicle = vehiclePersistence.applyVehicle(bus);
        log.info("Vehicle saved successfully with id={}", savedVehicle.getId());
        // Create seats
        if (vehicle.getTotalSeats() != null && vehicle.getTotalSeats() > 0) {

            int totalSeats = vehicle.getTotalSeats();

            log.info("Creating {} seats for vehicleId={}, busType={}", totalSeats, savedVehicle.getId(), vehicle.getBusType());
            List<Seats> seats = seatService.createSeatsForVehicle(savedVehicle, totalSeats);
            seatPersistence.saveAll(seats);
            log.info("Successfully created {} seats for vehicleId={}", seats.size(), savedVehicle.getId());
        } else {
            log.warn("Total seats not provided for vehicleNumber={}, skipping seat creation", vehicleNumber);
        }
        return VehicleResponse.builder()
                .vehicleNumber(savedVehicle.getVehicleNumber())
                .vehicleModel(savedVehicle.getVehicleModel())
                .vehicleColor(savedVehicle.getVehicleColor())
                .vehicleImage(savedVehicle.getVehicleImage())
                .busType(savedVehicle.getBusType())
                .totalSeats(vehicle.getTotalSeats())
                .build();
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
