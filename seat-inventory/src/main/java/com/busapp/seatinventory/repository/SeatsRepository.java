package com.busapp.seatinventory.repository;

import com.busapp.seatinventory.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<Seats, Integer> {

    List<Seats> findByBusId(Integer busId);
    
    Seats findByBusIdAndSeatNumber(Integer busId, String seatNumber);
}
