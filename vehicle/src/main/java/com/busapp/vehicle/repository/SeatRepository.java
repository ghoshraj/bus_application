package com.busapp.vehicle.repository;

import com.busapp.vehicle.entity.Seats;
import com.busapp.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seats, Integer> {

    List<Seats> findByVehicle(Vehicle vehicle);
    
    @Query("SELECT s FROM Seats s WHERE s.vehicle.id = :vehicleId")
    List<Seats> findByVehicleId(@Param("vehicleId") Integer vehicleId);
}
