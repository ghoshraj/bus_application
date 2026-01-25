package com.busapp.seatinventory.scheduler;

import com.busapp.seatinventory.repository.SeatLockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeatLockScheduler {

    private final SeatLockRepository seatLockRepository;

    /**
     * Runs every minute to unlock expired seats
     */
    @Scheduled(fixedRate = 60000) // 60000 milliseconds = 1 minute
    @Transactional
    public void unlockExpiredSeats() {
        Instant now = Instant.now();
        int unlockedCount = seatLockRepository.unlockExpiredSeats(now);
        if (unlockedCount > 0) {
            log.info("Unlocked {} expired seat(s)", unlockedCount);
        }
    }
}
