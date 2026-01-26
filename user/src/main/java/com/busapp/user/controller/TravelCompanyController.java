package com.busapp.user.controller;

import com.busapp.user.entity.TravelCompany;
import com.busapp.user.model.ErrorResponse;
import com.busapp.user.service.TravelCompanys;
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

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@Tag(name = "Company APIs", description = "APIs for managing travel companies")
public class TravelCompanyController {

    private final TravelCompanys travelCompanys;

    /**
     * 1️ Create / Apply Travel Company
     * Used when operator applies
     */
    @Operation(summary = "Apply for a new travel company")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Company created successfully",
                    content = @Content(schema = @Schema(implementation = TravelCompany.class))),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/apply")
    public ResponseEntity<TravelCompany> createCompany(
            @Valid @RequestBody TravelCompany travelCompany) {

        TravelCompany savedCompany = travelCompanys.addTravelCompany(travelCompany);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    /**
     * 2️ Get Company by ID
     * Used by Admin / Operator
     */
    @Operation(summary = "Get travel company by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company found",
                    content = @Content(schema = @Schema(implementation = TravelCompany.class))),
            @ApiResponse(responseCode = "404", description = "Company not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TravelCompany> getCompanyById(@PathVariable Integer id) {

        TravelCompany company = travelCompanys.getById(id);
        return ResponseEntity.ok(company);
    }

    /**
     * 3️ Get Company by Registration Number
     * Used for validation / duplicate check
     */
    @Operation(summary = "Get travel company by registration number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company found",
                    content = @Content(schema = @Schema(implementation = TravelCompany.class))),
            @ApiResponse(responseCode = "404", description = "Company not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/registration/{regNo}")
    public ResponseEntity<TravelCompany> getCompanyByRegistrationNumber(
            @PathVariable String regNo) {

        TravelCompany company =
                travelCompanys.getByCompanyRegistrationNumber(regNo);

        return ResponseEntity.ok(company);
    }
}
