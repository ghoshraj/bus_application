package com.busapp.route.service.persistence;

import com.busapp.route.entity.Route;

import java.util.List;
import java.util.Optional;

public interface RoutePersistence {

    Route saveRoute(Route route);
    
    Optional<Route> getRouteById(Integer id);
    
    Optional<Route> getRouteByOriginAndDestination(String origin, String destination);
    
    List<Route> getAllRoutes();
    
    List<Route> getRoutesByOrigin(String origin);
    
    List<Route> getRoutesByDestination(String destination);
}
