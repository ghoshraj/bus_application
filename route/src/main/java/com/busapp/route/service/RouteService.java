package com.busapp.route.service;

import com.busapp.route.entity.Route;

public interface RouteService {

    Route createRoute(Route route);
    
    Route getRouteById(Integer id);
    
    Route searchRoute(String source, String destination);
}
