package com.busapp.route.service.persistence.impl;

import com.busapp.route.entity.Route;
import com.busapp.route.repository.RouteRepository;
import com.busapp.route.service.persistence.RoutePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoutePersistenceImpl implements RoutePersistence {

    private final RouteRepository routeRepository;

    @Override
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Optional<Route> getRouteById(Integer id) {
        return routeRepository.findById(id);
    }

    @Override
    public Optional<Route> getRouteByOriginAndDestination(String origin, String destination) {
        return routeRepository.findByOriginAndDestination(origin, destination);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public List<Route> getRoutesByOrigin(String origin) {
        return routeRepository.findByOrigin(origin);
    }

    @Override
    public List<Route> getRoutesByDestination(String destination) {
        return routeRepository.findByDestination(destination);
    }
}
