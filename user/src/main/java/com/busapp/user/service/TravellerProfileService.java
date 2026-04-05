package com.busapp.user.service;

import com.busapp.user.entity.TravellerProfiles;
import com.busapp.user.model.TravellerProfileResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TravellerProfileService {

    void createProfile(TravellerProfiles profile) throws JsonProcessingException;

    TravellerProfileResponse getProfileByUserId(Integer userId);
}
