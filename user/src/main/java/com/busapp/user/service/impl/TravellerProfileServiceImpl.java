package com.busapp.user.service.impl;

import com.busapp.user.entity.TravellerProfiles;
import com.busapp.user.exception.GlobalExceptionEnums;
import com.busapp.user.exception.UserNotFound;
import com.busapp.user.service.TravellerProfileService;
import com.busapp.user.service.persistence.TravellerProfilePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TravellerProfileServiceImpl implements TravellerProfileService {

    private final TravellerProfilePersistence travellerProfilePersistence;

    @Override
    @Transactional
    public TravellerProfiles createProfile(TravellerProfiles profile) {
        profile.setCreatedAt(Instant.now());
        profile.setCreatedBy("");
        if (profile.getWalletBalance() == null) {
            profile.setWalletBalance(0L);
        }
        return travellerProfilePersistence.save(profile);
    }

    @Override
    public TravellerProfiles getProfileByUserId(Integer userId) {
        return travellerProfilePersistence.findByUserId(userId)
                .orElseThrow(() -> new UserNotFound(
                        GlobalExceptionEnums.USER_NOT_FOUND, userId));
    }
}
