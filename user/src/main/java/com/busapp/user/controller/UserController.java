package com.busapp.user.controller;

import com.busapp.user.entity.TravellerProfiles;
import com.busapp.user.model.ErrorResponse;
import com.busapp.user.service.TravellerProfileService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final TravellerProfileService travellerProfileService;

    @Operation(summary = "Create traveller profile")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Profile created successfully",
                    content = @Content(schema = @Schema(implementation = TravellerProfiles.class))),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/profile")
    public ResponseEntity<TravellerProfiles> createProfile(@Valid @RequestBody TravellerProfiles profile) {
        TravellerProfiles savedProfile = travellerProfileService.createProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    @Operation(summary = "Get traveller profile by user ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profile found",
                    content = @Content(schema = @Schema(implementation = TravellerProfiles.class))),
            @ApiResponse(responseCode = "404", description = "Profile not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TravellerProfiles> getUserById(@PathVariable Integer id) {
        TravellerProfiles profile = travellerProfileService.getProfileByUserId(id);
        return ResponseEntity.ok(profile);
    }
}
