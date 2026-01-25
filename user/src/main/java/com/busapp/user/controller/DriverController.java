package com.busapp.user.controller;

import com.busapp.user.entity.Operator;
import com.busapp.user.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final OperatorService operatorService;

    /**
     * POST /drivers
     */
    @PostMapping
    public ResponseEntity<Operator> createDriver(@RequestBody Operator operator) {
        Operator savedDriver = operatorService.createOperator(operator);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    /**
     * GET /drivers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Operator> getDriverById(@PathVariable Integer id) {
        Operator driver = operatorService.getDriverById(id);
        return ResponseEntity.ok(driver);
    }

    /**
     * GET /drivers/operator/{companyId}
     */
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
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<Operator> getDriverByVehicleId(@PathVariable Integer vehicleId) {
        Operator driver = operatorService.getDriverByVehicleId(vehicleId);
        return ResponseEntity.ok(driver);
    }
}
