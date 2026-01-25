package com.busapp.user.controller;

import com.busapp.user.entity.TravellerProfiles;
import com.busapp.user.service.TravellerProfileService;
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

    @PostMapping("/profile")
    public ResponseEntity<TravellerProfiles> createProfile(@Valid @RequestBody TravellerProfiles profile) {
        TravellerProfiles savedProfile = travellerProfileService.createProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravellerProfiles> getUserById(@PathVariable Integer id) {
        TravellerProfiles profile = travellerProfileService.getProfileByUserId(id);
        return ResponseEntity.ok(profile);
    }
}
