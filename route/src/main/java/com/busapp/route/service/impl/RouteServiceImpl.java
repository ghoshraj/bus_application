package com.busapp.route.service.impl;

import com.busapp.route.entity.Route;
import com.busapp.route.enums.Status;
import com.busapp.route.exception.GlobalExceptionEnums;
import com.busapp.route.exception.RouteAlreadyExists;
import com.busapp.route.exception.RouteNotFound;
import com.busapp.route.service.RouteService;
import com.busapp.route.service.persistence.RoutePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RoutePersistence routePersistence;

    @Override
    @Transactional
    public Route createRoute(Route route) {
        // Check if route already exists
        Optional<Route> existingRoute = routePersistence.getRouteByOriginAndDestination(
                route.getOrigin(), route.getDestination());
        
        if (existingRoute.isPresent()) {
            throw new RouteAlreadyExists(GlobalExceptionEnums.ROUTE_ALREADY_EXIST, 
                    route.getOrigin(), route.getDestination());
        }
        
        // Set default values
        if (route.getStatus() == null) {
            route.setStatus(Status.ACTIVE);
        }
        route.setCreatedAt(Instant.now());
        route.setCreatedBy("");
        
        return routePersistence.saveRoute(route);
    }

    @Override
    public Route getRouteById(Integer id) {
        return routePersistence.getRouteById(id)
                .orElseThrow(() -> new RouteNotFound(GlobalExceptionEnums.ROUTE_NOT_FOUND, id));
    }

    @Override
    public Route searchRoute(String source, String destination) {
        return routePersistence.getRouteByOriginAndDestination(source, destination)
                .orElseThrow(() -> new RouteNotFound(
                        GlobalExceptionEnums.ROUTE_NOT_FOUND_BY_SOURCE_DEST, source, destination));
    }
}
