package com.busapp.user.service.persistence;

import com.busapp.user.entity.TravellerProfiles;

import java.util.Optional;

public interface TravellerProfilePersistence {

    TravellerProfiles save(TravellerProfiles profile);
    
    Optional<TravellerProfiles> findByUserId(Integer userId);
}
