package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.constant.ApiConstants.*;

@RestController
@RequestMapping(SUPER_ADMIN_BASE_PATH)
@RequiredArgsConstructor
@Tag(name = SUPER_ADMIN_TAG, description = SUPER_ADMIN_TAG_DESC)
@SecurityRequirement(name = BEARER_AUTH)
public class SuperAdminController {

    private final UserService userService;

    @PostMapping(ASSIGN_ROLES)
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN)
    @Operation(summary = ASSIGN_ROLES_SUMMARY, description = ASSIGN_ROLES_DESC)
    @ApiResponses({
            @ApiResponse(responseCode = OK_200, description = ASSIGN_ROLE_SUCCESS,
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = BAD_REQUEST_400, description = BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class))),
            @ApiResponse(responseCode = UNAUTHORIZED_401, description = UNAUTHORIZED,
                    content = @Content(schema = @Schema(implementation = UnauthorizedErrorResponse.class))),
            @ApiResponse(responseCode = FORBIDDEN_403, description = FORBIDDEN,
                    content = @Content(schema = @Schema(implementation = ForbiddenErrorResponse.class)))
    })
    public ResponseEntity<UserResponse> assignRoles(@Valid @RequestBody AssignRolesRequest request) {

        UserResponse response = userService.assignRoles(request.getUserId(), request.getRoles());

        return ResponseEntity.ok(response);
    }
}
