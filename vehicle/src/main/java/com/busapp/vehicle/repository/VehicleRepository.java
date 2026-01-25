package com.busapp.vehicle.repository;

import com.busapp.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);
    
    List<Vehicle> findByCompanyId(int companyId);
}
