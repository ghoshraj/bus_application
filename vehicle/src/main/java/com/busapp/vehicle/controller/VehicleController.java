package com.busapp.vehicle.controller;

import com.busapp.vehicle.entity.Vehicle;
import com.busapp.vehicle.model.ErrorResponse;
import com.busapp.vehicle.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle")
@Tag(name = "Vehicle APIs", description = "Bus and vehicle management APIs")
public class VehicleController {

    private final VehicleService vehicleService;

    @Operation(summary = "Create vehicle", description = "Create a new vehicle. Accessible by ADMIN role.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vehicle created successfully",
                    content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden - Access denied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/applyvehicle")
    public ResponseEntity<Vehicle> createVehicle(
            @Valid @RequestBody Vehicle vehicle) {

        Vehicle savedVehicle = vehicleService.applyVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @Operation(summary = "Create bus", description = "Create a new bus vehicle. Accessible by ADMIN role.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Bus created successfully",
                    content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden - Access denied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/applybuse")
    public ResponseEntity<Vehicle> createBus(
            @Valid @RequestBody Vehicle vehicle) {

        Vehicle savedVehicle = vehicleService.applyVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @Operation(summary = "Get bus by ID", description = "Fetch bus details by bus ID. Accessible by USER and BUS_DRIVER roles.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bus fetched successfully",
                    content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden - Access denied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Bus not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/buses/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Integer id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @Operation(summary = "Get buses by company ID", description = "Fetch all buses belonging to a company. Accessible by USER and BUS_DRIVER roles.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Buses fetched successfully",
                    content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden - Access denied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Company or buses not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/buses/company/{companyId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByCompanyId(@PathVariable int companyId) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByCompanyId(companyId);
        return ResponseEntity.ok(vehicles);
    }
}
