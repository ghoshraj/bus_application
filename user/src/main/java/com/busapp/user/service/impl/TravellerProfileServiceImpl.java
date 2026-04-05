package com.busapp.user.service.impl;

import com.busapp.user.entity.TravellerProfiles;
import com.busapp.user.exception.GlobalExceptionEnums;
import com.busapp.user.exception.UserNotFound;
import com.busapp.user.messagebroker.model.ProfileUpdateRequest;
import com.busapp.user.messagebroker.producer.KafkaProducer;
import com.busapp.user.model.TravellerProfileResponse;
import com.busapp.user.service.TravellerProfileService;
import com.busapp.user.service.persistence.TravellerProfilePersistence;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravellerProfileServiceImpl implements TravellerProfileService {

    private final String systemUser = "System";
    private final TravellerProfilePersistence travellerProfilePersistence;
    private final KafkaProducer kafkaProducer;

    @Override
    @Transactional
    public void createProfile(TravellerProfiles profile) throws JsonProcessingException {
        log.info("Creating profile for user {}", profile.getUserId());
        Optional<TravellerProfiles> travellerProfilesExist = travellerProfilePersistence.findByUserId(profile.getUserId());
        if (travellerProfilesExist.isPresent()) {
            log.info("Profile already exists for user {}", profile.getUserId());
            kafkaProducer.sendProfileRequest(new ProfileUpdateRequest(profile.getUserId()));
            return;
        }
        TravellerProfiles travellerProfiles = new TravellerProfiles();
        travellerProfiles.setCreatedAt(Instant.now());
        travellerProfiles.setCreatedBy(systemUser);
        travellerProfiles.setUserId(profile.getUserId());
        travellerProfiles.setName(profile.getName());
        travellerProfiles.setJoiningDate(LocalDate.now());
        travellerProfiles.setGender(profile.getGender());
        travellerProfiles.setPhoneNumber(profile.getPhoneNumber());
        travellerProfiles.setTotalTrip(0);
        travellerProfiles.setTotalTravel(0);
        travellerProfiles.setWalletBalance(0L);
        travellerProfilePersistence.save(travellerProfiles);
        log.info("Profile created successfully for user {}", profile.getUserId());
        kafkaProducer.sendProfileRequest(new ProfileUpdateRequest(travellerProfiles.getUserId()));
    }

    @Override
    public TravellerProfileResponse getProfileByUserId(Integer userId) {
        log.info("Fetching traveller profile for userId: {}", userId);
        TravellerProfiles profiles = travellerProfilePersistence.findByUserId(userId)
                .orElseThrow(() -> new UserNotFound(
                        GlobalExceptionEnums.USER_NOT_FOUND, userId));
        TravellerProfileResponse profileResponse = new TravellerProfileResponse();
        profileResponse.setUserName(profiles.getName());
        profileResponse.setPhoneNumber(profiles.getPhoneNumber());
        profileResponse.setTotalTravel(profiles.getTotalTravel());
        profileResponse.setTotalTrip(profiles.getTotalTrip());
        profileResponse.setJoiningDate(profiles.getJoiningDate());
        profileResponse.setWalletBalance(profiles.getWalletBalance());
        log.info("Successfully fetched traveller profile for userId: {}", userId);
        return profileResponse;
    }
}
