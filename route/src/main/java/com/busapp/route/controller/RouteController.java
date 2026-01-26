package com.busapp.route.controller;

import com.busapp.route.entity.Route;
import com.busapp.route.model.ErrorResponse;
import com.busapp.route.service.RouteService;
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
@RequestMapping("/routes")
@RequiredArgsConstructor
@Tag(name = "Route APIs", description = "Route creation and search APIs")
public class RouteController {

    private final RouteService routeService;

    @Operation(summary = "Create route",
            description = "Create a new travel route. Accessible by ADMIN role only.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Route created successfully",
                    content = @Content(schema = @Schema(implementation = Route.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden - Access denied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/apply")
    public ResponseEntity<Route> createRoute(@Valid @RequestBody Route route) {
        Route savedRoute = routeService.createRoute(route);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoute);
    }

    @Operation(summary = "Get route by ID",
            description = "Fetch route details by route ID. Accessible by USER and BUS_DRIVER roles.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Route fetched successfully",
                    content = @Content(schema = @Schema(implementation = Route.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden - Access denied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Route not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Integer id) {
        Route route = routeService.getRouteById(id);
        return ResponseEntity.ok(route);
    }

    @Operation(summary = "Search route by source and destination",
            description = "Search route using source and destination city names. Accessible by USER and BUS_DRIVER roles.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Route found successfully",
                    content = @Content(schema = @Schema(implementation = Route.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden - Access denied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Route not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/search")
    public ResponseEntity<Route> searchRoute(
            @RequestParam String source,
            @RequestParam String dest) {
        Route route = routeService.searchRoute(source, dest);
        return ResponseEntity.ok(route);
    }
}
