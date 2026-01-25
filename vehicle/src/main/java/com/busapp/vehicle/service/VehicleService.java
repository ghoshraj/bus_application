package com.busapp.vehicle.service;

import com.busapp.vehicle.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    Vehicle applyVehicle(Vehicle vehicle);
    
    Vehicle getVehicleById(Integer id);
    
    List<Vehicle> getVehiclesByCompanyId(int companyId);
}
