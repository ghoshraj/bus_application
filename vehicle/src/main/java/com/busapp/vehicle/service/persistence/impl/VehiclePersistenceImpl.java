package com.busapp.vehicle.service.persistence.impl;

import com.busapp.vehicle.entity.Vehicle;
import com.busapp.vehicle.repository.VehicleRepository;
import com.busapp.vehicle.service.persistence.VehiclePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiclePersistenceImpl implements VehiclePersistence {

    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicle applyVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Optional<Vehicle> getVehicleByNumber(String vehicleNumber) {
        return vehicleRepository.findByVehicleNumber(vehicleNumber);
    }

    @Override
    public Optional<Vehicle> getVehicleById(Integer id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public List<Vehicle> getVehiclesByCompanyId(int companyId) {
        return vehicleRepository.findByCompanyId(companyId);
    }
}
