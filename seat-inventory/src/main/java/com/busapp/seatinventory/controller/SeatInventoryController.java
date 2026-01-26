package com.busapp.seatinventory.controller;

import com.busapp.seatinventory.model.ErrorResponse;
import com.busapp.seatinventory.model.LockRequest;
import com.busapp.seatinventory.model.SeatAvailabilityResponse;
import com.busapp.seatinventory.model.UnlockRequest;
import com.busapp.seatinventory.service.SeatInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Lock a seat",
            description = "Locks a seat for 10 minutes to prevent other users from booking it")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Seat locked successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/lock")
    public ResponseEntity<String> lockSeat(@Valid @RequestBody LockRequest lockRequest) {
        seatInventoryService.lockSeat(lockRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Seat locked successfully for 10 minutes");
    }


    @Operation(summary = "Unlock a seat", description = "Unlocks a previously locked seat")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Seat unlocked successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/unlock")
    public ResponseEntity<String> unlockSeat(@Valid @RequestBody UnlockRequest unlockRequest) {
        seatInventoryService.unlockSeat(unlockRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Seat unlocked successfully");
    }

    @Operation(summary = "Get seat availability", description = "Fetch seat availability for a given schedule ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Seat availability fetched successfully",
                    content = @Content(schema = @Schema(implementation = SeatAvailabilityResponse.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/availability/{scheduleId}")
    public ResponseEntity<SeatAvailabilityResponse> getSeatAvailabilityByScheduleId(@PathVariable Integer scheduleId) {
        SeatAvailabilityResponse availability = seatInventoryService.getSeatAvailabilityByScheduleId(scheduleId);
        return ResponseEntity.ok(availability);
    }
}
