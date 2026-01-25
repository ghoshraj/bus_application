package com.busapp.vehicle.service.persistence;

import com.busapp.vehicle.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehiclePersistence {

    Vehicle applyVehicle(Vehicle vehicle);

    Optional<Vehicle> getVehicleByNumber(String vehicleNumber);
    
    Optional<Vehicle> getVehicleById(Integer id);
    
    List<Vehicle> getVehiclesByCompanyId(int companyId);
}
