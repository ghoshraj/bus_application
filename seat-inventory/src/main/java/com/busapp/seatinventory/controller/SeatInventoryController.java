package com.busapp.seatinventory.controller;

import com.busapp.seatinventory.model.LockRequest;
import com.busapp.seatinventory.model.SeatAvailabilityResponse;
import com.busapp.seatinventory.model.UnlockRequest;
import com.busapp.seatinventory.service.SeatInventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatInventoryController {

    private final SeatInventoryService seatInventoryService;

    @PostMapping("/lock")
    public ResponseEntity<String> lockSeat(@Valid @RequestBody LockRequest lockRequest) {
        seatInventoryService.lockSeat(lockRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Seat locked successfully for 10 minutes");
    }

    @PostMapping("/unlock")
    public ResponseEntity<String> unlockSeat(@Valid @RequestBody UnlockRequest unlockRequest) {
        seatInventoryService.unlockSeat(unlockRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Seat unlocked successfully");
    }

    @GetMapping("/availability/{scheduleId}")
    public ResponseEntity<SeatAvailabilityResponse> getSeatAvailabilityByScheduleId(@PathVariable Integer scheduleId) {
        SeatAvailabilityResponse availability = seatInventoryService.getSeatAvailabilityByScheduleId(scheduleId);
        return ResponseEntity.ok(availability);
    }
}
