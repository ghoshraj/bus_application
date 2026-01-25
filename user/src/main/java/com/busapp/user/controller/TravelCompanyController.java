package com.busapp.user.controller;

import com.busapp.user.entity.TravelCompany;
import com.busapp.user.service.TravelCompanys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class TravelCompanyController {

    private final TravelCompanys travelCompanys;

    /**
     * 1️⃣ Create / Apply Travel Company
     * Used when operator applies
     */
    @PostMapping("/apply")
    public ResponseEntity<TravelCompany> createCompany(
            @Valid @RequestBody TravelCompany travelCompany) {

        TravelCompany savedCompany = travelCompanys.addTravelCompany(travelCompany);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    /**
     * 2️⃣ Get Company by ID
     * Used by Admin / Operator
     */
    @GetMapping("/{id}")
    public ResponseEntity<TravelCompany> getCompanyById(@PathVariable Integer id) {

        TravelCompany company = travelCompanys.getById(id);
        return ResponseEntity.ok(company);
    }

    /**
     * 3️⃣ Get Company by Registration Number
     * Used for validation / duplicate check
     */
    @GetMapping("/registration/{regNo}")
    public ResponseEntity<TravelCompany> getCompanyByRegistrationNumber(
            @PathVariable String regNo) {

        TravelCompany company =
                travelCompanys.getByCompanyRegistrationNumber(regNo);

        return ResponseEntity.ok(company);
    }
}
