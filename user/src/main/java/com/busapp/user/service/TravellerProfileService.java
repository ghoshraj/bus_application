package com.busapp.user.service;

import com.busapp.user.entity.TravellerProfiles;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TravellerProfileService {

    void createProfile(String msg) throws JsonProcessingException;
    
    TravellerProfiles getProfileByUserId(Integer userId);
}
