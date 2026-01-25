package com.busapp.seatinventory.service.persistence.impl;

import com.busapp.seatinventory.entity.SeatLock;
import com.busapp.seatinventory.entity.Seats;
import com.busapp.seatinventory.repository.SeatLockRepository;
import com.busapp.seatinventory.repository.SeatsRepository;
import com.busapp.seatinventory.service.persistence.SeatInventoryPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatInventoryPersistenceImpl implements SeatInventoryPersistence {

    private final SeatsRepository seatsRepository;
    private final SeatLockRepository seatLockRepository;

    @Override
    public List<Seats> getSeatsByBusId(Integer busId) {
        return seatsRepository.findByBusId(busId);
    }

    @Override
    public Optional<Seats> getSeatByBusIdAndSeatNumber(Integer busId, String seatNumber) {
        return Optional.ofNullable(seatsRepository.findByBusIdAndSeatNumber(busId, seatNumber));
    }

    @Override
    public SeatLock saveSeatLock(SeatLock seatLock) {
        return seatLockRepository.save(seatLock);
    }

    @Override
    public Optional<SeatLock> getActiveLockByBusIdAndSeatNumber(Integer busId, String seatNumber) {
        return seatLockRepository.findByBusIdAndSeatNumberAndIsActiveTrue(busId, seatNumber);
    }

    @Override
    public List<SeatLock> getActiveLocksByBusId(Integer busId) {
        return seatLockRepository.findByBusIdAndIsActiveTrue(busId);
    }

    @Override
    public void deleteSeatLock(SeatLock seatLock) {
        seatLockRepository.delete(seatLock);
    }
}
