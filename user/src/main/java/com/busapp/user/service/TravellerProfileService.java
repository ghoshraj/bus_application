package com.busapp.user.service;

import com.busapp.user.entity.TravellerProfiles;

public interface TravellerProfileService {

    TravellerProfiles createProfile(TravellerProfiles profile);
    
    TravellerProfiles getProfileByUserId(Integer userId);
}
