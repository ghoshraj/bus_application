package com.busapp.vehicle.controller;

import com.busapp.vehicle.entity.Seats;
import com.busapp.vehicle.model.ErrorResponse;
import com.busapp.vehicle.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
@Tag(name = "Seat APIs", description = "Seat management and availability APIs")
public class SeatController {

    private final SeatService seatService;

    @Operation(summary = "Get seats by bus ID", description = "Fetch all seat details for a given bus. Accessible by USER and BUS_DRIVER roles.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Seats fetched successfully",
                    content = @Content(schema = @Schema(implementation = Seats.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden - User does not have permission",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Bus or seats not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<Seats>> getSeatsByBusId(@PathVariable Integer busId) {
        List<Seats> seats = seatService.getSeatsByBusId(busId);
        return ResponseEntity.ok(seats);
    }
}
