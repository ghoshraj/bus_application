package com.busapp.route.repository;

import com.busapp.route.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    Optional<Route> findByOriginAndDestination(String origin, String destination);
    
    List<Route> findByOrigin(String origin);
    
    List<Route> findByDestination(String destination);
    
    List<Route> findByStatus(com.busapp.route.enums.Status status);
}
