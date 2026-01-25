package com.busapp.route.controller;

import com.busapp.route.entity.Route;
import com.busapp.route.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<Route> createRoute(@Valid @RequestBody Route route) {
        Route savedRoute = routeService.createRoute(route);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoute);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Integer id) {
        Route route = routeService.getRouteById(id);
        return ResponseEntity.ok(route);
    }

    @GetMapping("/search")
    public ResponseEntity<Route> searchRoute(
            @RequestParam String source,
            @RequestParam String dest) {
        Route route = routeService.searchRoute(source, dest);
        return ResponseEntity.ok(route);
    }
}
