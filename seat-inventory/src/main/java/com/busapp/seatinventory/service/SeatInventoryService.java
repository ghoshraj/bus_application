package com.busapp.seatinventory.service;

import com.busapp.seatinventory.model.LockRequest;
import com.busapp.seatinventory.model.SeatAvailabilityResponse;
import com.busapp.seatinventory.model.UnlockRequest;

public interface SeatInventoryService {

    void lockSeat(LockRequest lockRequest);
    
    void unlockSeat(UnlockRequest unlockRequest);
    
    SeatAvailabilityResponse getSeatAvailabilityByScheduleId(Integer scheduleId);
}
