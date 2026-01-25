package com.busapp.vehicle.controller;

import com.busapp.vehicle.entity.Vehicle;
import com.busapp.vehicle.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> createVehicle(
            @Valid @RequestBody Vehicle vehicle) {

        Vehicle savedVehicle = vehicleService.applyVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @PostMapping("/buses")
    public ResponseEntity<Vehicle> createBus(
            @Valid @RequestBody Vehicle vehicle) {

        Vehicle savedVehicle = vehicleService.applyVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @GetMapping("/buses/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Integer id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/buses/company/{companyId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByCompanyId(@PathVariable int companyId) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByCompanyId(companyId);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/buses/operator/{operatorId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByOperatorId(@PathVariable int operatorId) {
        // For now, using companyId - can be enhanced later
        List<Vehicle> vehicles = vehicleService.getVehiclesByCompanyId(operatorId);
        return ResponseEntity.ok(vehicles);
    }
}
