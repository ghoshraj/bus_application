package com.example.demo.controller;

import com.example.demo.model.ForbiddenErrorResponse;
import com.example.demo.model.UnauthorizedErrorResponse;
import com.example.demo.model.UserResponse;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.constant.ApiConstants.*;
import static com.example.demo.constant.SecurityConstants.ROLE_ADMIN;

@RestController
@RequestMapping(ADMIN_BASE_PATH)
@RequiredArgsConstructor
@Tag(name = ROLE_ADMIN, description = ADMIN_TAG_DESC)
@SecurityRequirement(name = BEARER_AUTH)
public class AdminController {

    private final UserService userService;

    @GetMapping(GET_ALL_USERS)
    @PreAuthorize(HAS_ROLE_ADMIN)
    @Operation(summary = GET_ALL_USERS_SUMMARY, description = GET_ALL_USERS_DESC)
    @ApiResponses({
            @ApiResponse(responseCode = OK_200, description = OK,
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = UNAUTHORIZED_401, description = UNAUTHORIZED,
                    content = @Content(schema = @Schema(implementation = UnauthorizedErrorResponse.class))),
            @ApiResponse(responseCode = FORBIDDEN_403, description = FORBIDDEN,
                    content = @Content(schema = @Schema(implementation = ForbiddenErrorResponse.class)))
    })
    public ResponseEntity<List<UserResponse>> getAllUser() {
        List<UserResponse> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
}
