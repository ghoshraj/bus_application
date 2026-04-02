package com.busapp.user.service.impl;

import com.busapp.user.config.ObjectMapperHelper;
import com.busapp.user.entity.TravellerProfiles;
import com.busapp.user.exception.GlobalExceptionEnums;
import com.busapp.user.exception.UserNotFound;
import com.busapp.user.service.TravellerProfileService;
import com.busapp.user.service.persistence.TravellerProfilePersistence;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TravellerProfileServiceImpl implements TravellerProfileService {

    private final TravellerProfilePersistence travellerProfilePersistence;
    private final ObjectMapperHelper objectMapperHelper;

    @Override
    @Transactional
    public void createProfile(String msg) throws JsonProcessingException {
        TravellerProfiles profile = objectMapperHelper.fromJson(msg, TravellerProfiles.class);
        TravellerProfiles travellerProfiles = new TravellerProfiles();
        travellerProfiles.setCreatedAt(Instant.now());
        travellerProfiles.setCreatedBy("");
        travellerProfiles.setUserId(profile.getUserId());
        travellerProfiles.setName(profile.getName());
        travellerProfiles.setJoiningDate(LocalDate.now());
        travellerProfiles.setGender(profile.getGender());
        travellerProfiles.setPhoneNumber(profile.getPhoneNumber());
        travellerProfiles.setTotalTrip(0);
        travellerProfiles.setTotalTravel(0);
        travellerProfiles.setWalletBalance(0L);
        travellerProfilePersistence.save(travellerProfiles);
    }

    @Override
    public TravellerProfiles getProfileByUserId(Integer userId) {
        return travellerProfilePersistence.findByUserId(userId)
                .orElseThrow(() -> new UserNotFound(
                        GlobalExceptionEnums.USER_NOT_FOUND, userId));
    }
}
