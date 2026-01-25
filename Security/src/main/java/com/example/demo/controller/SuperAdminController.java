package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.model.AssignRolesRequest;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/super-admin")
@RequiredArgsConstructor
public class SuperAdminController {

    private final UserService userService;

    /**
     * Assign roles to a user
     * Only SUPER_ADMIN can access this endpoint
     * Can promote users to BUS_DRIVER, ADMIN, or assign multiple roles
     */
    @PostMapping("/assign-roles")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> assignRoles(@Valid @RequestBody AssignRolesRequest request) {
        User updatedUser = userService.assignRoles(request.getUserId(), request.getRoles());
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
}
