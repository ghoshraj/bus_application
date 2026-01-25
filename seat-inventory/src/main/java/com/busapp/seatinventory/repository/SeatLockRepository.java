package com.busapp.seatinventory.repository;

import com.busapp.seatinventory.entity.SeatLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeatLockRepository extends JpaRepository<SeatLock, Integer> {

    Optional<SeatLock> findByBusIdAndSeatNumberAndIsActiveTrue(Integer busId, String seatNumber);
    
    List<SeatLock> findByBusIdAndIsActiveTrue(Integer busId);
    
    @Modifying
    @Query("UPDATE SeatLock sl SET sl.isActive = false WHERE sl.expiresAt < :currentTime AND sl.isActive = true")
    int unlockExpiredSeats(@Param("currentTime") Instant currentTime);
    
    @Query("SELECT sl FROM SeatLock sl WHERE sl.busId = :busId AND sl.isActive = true AND sl.expiresAt > :currentTime")
    List<SeatLock> findActiveLocksByBusId(@Param("busId") Integer busId, @Param("currentTime") Instant currentTime);
}
