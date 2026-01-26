package com.busapp.user.controller;

import com.busapp.user.entity.Operator;
import com.busapp.user.model.ErrorResponse;
import com.busapp.user.service.OperatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies/drivers")
@RequiredArgsConstructor
@Tag(name = "Driver APIs", description = "APIs for managing drivers")
public class DriverController {

    private final OperatorService operatorService;

    /**
     * POST /drivers
     */
    @Operation(summary = "Create a new driver")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Driver created successfully",
                    content = @Content(schema = @Schema(implementation = Operator.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/apply")
    public ResponseEntity<Operator> createDriver(@RequestBody Operator operator) {
        Operator savedDriver = operatorService.createOperator(operator);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    /**
     * GET /drivers/{id}
     */
    @Operation(summary = "Get driver by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Driver found",
                    content = @Content(schema = @Schema(implementation = Operator.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Driver not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Operator> getDriverById(@PathVariable Integer id) {
        Operator driver = operatorService.getDriverById(id);
        return ResponseEntity.ok(driver);
    }

    /**
     * GET /drivers/operator/{companyId}
     */
    @Operation(summary = "Get drivers by company ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Drivers fetched successfully",
                    content = @Content(schema = @Schema(implementation = Operator.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/operator/{companyId}")
    public ResponseEntity<List<Operator>> getDriversByCompanyId(
            @PathVariable Integer companyId) {

        List<Operator> drivers =
                operatorService.getDriversByCompanyId(companyId);

        return ResponseEntity.ok(drivers);
    }

    /**
     * GET /drivers/vehicle/{vehicleId}
     */
    @Operation(summary = "Get driver by vehicle ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Driver found",
                    content = @Content(schema = @Schema(implementation = Operator.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<Operator> getDriverByVehicleId(@PathVariable Integer vehicleId) {
        Operator driver = operatorService.getDriverByVehicleId(vehicleId);
        return ResponseEntity.ok(driver);
    }
}
