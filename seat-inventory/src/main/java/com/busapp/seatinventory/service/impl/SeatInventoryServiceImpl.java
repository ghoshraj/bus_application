package com.busapp.seatinventory.service.impl;

import com.busapp.seatinventory.entity.SeatLock;
import com.busapp.seatinventory.entity.Seats;
import com.busapp.seatinventory.exception.GlobalExceptionEnums;
import com.busapp.seatinventory.exception.ScheduleNotFound;
import com.busapp.seatinventory.exception.SeatAlreadyLocked;
import com.busapp.seatinventory.exception.SeatNotFound;
import com.busapp.seatinventory.exception.SeatNotLocked;
import com.busapp.seatinventory.model.LockRequest;
import com.busapp.seatinventory.model.ScheduleResponse;
import com.busapp.seatinventory.model.SeatAvailabilityResponse;
import com.busapp.seatinventory.model.UnlockRequest;
import com.busapp.seatinventory.service.SeatInventoryService;
import com.busapp.seatinventory.service.client.ScheduleServiceClient;
import com.busapp.seatinventory.service.persistence.SeatInventoryPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryPersistence seatInventoryPersistence;
    private final ScheduleServiceClient scheduleServiceClient;
    private static final int LOCK_DURATION_MINUTES = 10;

    @Override
    @Transactional
    public void lockSeat(LockRequest lockRequest) {
        Integer busId = lockRequest.getBusId();
        String seatNumber = lockRequest.getSeatNumber();
        
        // Check if seat exists
        Optional<Seats> seat = seatInventoryPersistence.getSeatByBusIdAndSeatNumber(busId, seatNumber);
        if (seat.isEmpty()) {
            throw new SeatNotFound(GlobalExceptionEnums.SEAT_NOT_FOUND, busId, seatNumber);
        }
        
        // Check if seat is already locked
        Optional<SeatLock> existingLock = seatInventoryPersistence.getActiveLockByBusIdAndSeatNumber(busId, seatNumber);
        if (existingLock.isPresent()) {
            // Check if lock is still valid
            SeatLock lock = existingLock.get();
            if (lock.getExpiresAt().isAfter(Instant.now())) {
                throw new SeatAlreadyLocked(GlobalExceptionEnums.SEAT_ALREADY_LOCKED, busId, seatNumber);
            } else {
                // Lock expired, delete it and create new one
                seatInventoryPersistence.deleteSeatLock(lock);
            }
        }
        
        // Create new lock
        Instant now = Instant.now();
        Instant expiresAt = now.plusSeconds(LOCK_DURATION_MINUTES * 60L);
        
        SeatLock seatLock = new SeatLock();
        seatLock.setBusId(busId);
        seatLock.setSeatNumber(seatNumber);
        seatLock.setLockedAt(now);
        seatLock.setExpiresAt(expiresAt);
        seatLock.setIsActive(true);
        
        seatInventoryPersistence.saveSeatLock(seatLock);
    }

    @Override
    @Transactional
    public void unlockSeat(UnlockRequest unlockRequest) {
        Integer busId = unlockRequest.getBusId();
        String seatNumber = unlockRequest.getSeatNumber();
        
        // Check if seat is locked
        Optional<SeatLock> seatLock = seatInventoryPersistence.getActiveLockByBusIdAndSeatNumber(busId, seatNumber);
        if (seatLock.isEmpty()) {
            throw new SeatNotLocked(GlobalExceptionEnums.SEAT_NOT_LOCKED, busId, seatNumber);
        }
        
        // Unlock the seat
        SeatLock lock = seatLock.get();
        lock.setIsActive(false);
        seatInventoryPersistence.saveSeatLock(lock);
    }

    @Override
    public SeatAvailabilityResponse getSeatAvailabilityByScheduleId(Integer scheduleId) {
        // Get schedule to extract busId
        ScheduleResponse schedule = scheduleServiceClient.getScheduleById(scheduleId)
                .block(); // Blocking call - in production consider using reactive approach
        
        if (schedule == null || schedule.getBusId() == null) {
            throw new ScheduleNotFound(GlobalExceptionEnums.SCHEDULE_NOT_FOUND, scheduleId);
        }
        
        Integer busId = schedule.getBusId();
        
        // Get all seats for the bus
        List<Seats> allSeats = seatInventoryPersistence.getSeatsByBusId(busId);
        
        // Get all active locks for the bus (only non-expired locks)
        Instant now = Instant.now();
        List<SeatLock> activeLocks = seatInventoryPersistence.getActiveLocksByBusId(busId);
        
        // Filter out expired locks and get locked seat numbers
        List<String> lockedSeatNumbers = activeLocks.stream()
                .filter(lock -> lock.getIsActive() && lock.getExpiresAt().isAfter(now))
                .map(SeatLock::getSeatNumber)
                .collect(Collectors.toList());
        
        // Get available seats (seats that are not in the locked list)
        List<String> availableSeatNumbers = allSeats.stream()
                .map(Seats::getSeatNumber)
                .filter(seatNum -> !lockedSeatNumbers.contains(seatNum))
                .collect(Collectors.toList());
        
        SeatAvailabilityResponse response = new SeatAvailabilityResponse();
        response.setBusId(busId);
        response.setAvailableSeats(availableSeatNumbers);
        response.setLockedSeats(lockedSeatNumbers);
        response.setTotalSeats(allSeats.size());
        response.setAvailableCount(availableSeatNumbers.size());
        response.setLockedCount(lockedSeatNumbers.size());
        
        return response;
    }
}
