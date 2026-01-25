package com.busapp.seatinventory.service.persistence;

import com.busapp.seatinventory.entity.SeatLock;
import com.busapp.seatinventory.entity.Seats;

import java.util.List;
import java.util.Optional;

public interface SeatInventoryPersistence {

    List<Seats> getSeatsByBusId(Integer busId);
    
    Optional<Seats> getSeatByBusIdAndSeatNumber(Integer busId, String seatNumber);
    
    SeatLock saveSeatLock(SeatLock seatLock);
    
    Optional<SeatLock> getActiveLockByBusIdAndSeatNumber(Integer busId, String seatNumber);
    
    List<SeatLock> getActiveLocksByBusId(Integer busId);
    
    void deleteSeatLock(SeatLock seatLock);
}
