package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.model.AssignRolesRequest;
import com.example.demo.model.ErrorResponse;
import com.example.demo.model.UserResponse;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/super-admin")
@RequiredArgsConstructor
@Tag(name = "Super Admin", description = "Super-admin endpoints")
@SecurityRequirement(name = "bearerAuth")
public class SuperAdminController {

    private final UserService userService;

    /**
     * Assign roles to a user
     * Only SUPER_ADMIN can access this endpoint
     * Can promote users to BUS_DRIVER, ADMIN, or assign multiple roles
     */
    @PostMapping("/assign-roles")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "Assign roles", description = "Assigns one or more roles to a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated successfully",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<UserResponse> assignRoles(@Valid @RequestBody AssignRolesRequest request) {
        UserResponse response = userService.assignRoles(request.getUserId(), request.getRoles());
        return ResponseEntity.ok().body(response);
    }
}
