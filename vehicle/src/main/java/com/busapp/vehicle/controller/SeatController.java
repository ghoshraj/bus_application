package com.busapp.vehicle.controller;

import com.busapp.vehicle.entity.Seats;
import com.busapp.vehicle.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<Seats>> getSeatsByBusId(@PathVariable Integer busId) {
        List<Seats> seats = seatService.getSeatsByBusId(busId);
        return ResponseEntity.ok(seats);
    }
}
