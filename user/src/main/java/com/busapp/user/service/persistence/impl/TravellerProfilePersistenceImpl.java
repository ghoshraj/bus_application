package com.busapp.user.service.persistence.impl;

import com.busapp.user.entity.TravellerProfiles;
import com.busapp.user.repository.TravellerProfileRepository;
import com.busapp.user.service.persistence.TravellerProfilePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravellerProfilePersistenceImpl implements TravellerProfilePersistence {

    private final TravellerProfileRepository travellerProfileRepository;

    @Override
    public TravellerProfiles save(TravellerProfiles profile) {
        return travellerProfileRepository.save(profile);
    }

    @Override
    public Optional<TravellerProfiles> findByUserId(Integer userId) {
        return travellerProfileRepository.findByUserId(userId);
    }
}
