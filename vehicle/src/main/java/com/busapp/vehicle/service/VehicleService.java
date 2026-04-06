package com.busapp.vehicle.service;

import com.busapp.vehicle.entity.Vehicle;
import com.busapp.vehicle.model.VehicleRequest;
import com.busapp.vehicle.model.VehicleResponse;

import java.util.List;

public interface VehicleService {

    VehicleResponse applyVehicle(VehicleRequest vehicle);
    
    Vehicle getVehicleById(Integer id);
    
    List<Vehicle> getVehiclesByCompanyId(int companyId);
}
