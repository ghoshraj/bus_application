package com.busapp.user.service.persistence.impl;

import com.busapp.user.entity.TravelCompany;
import com.busapp.user.repository.TravelCompanyRepo;
import com.busapp.user.service.persistence.TravelCompanyPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TravelCompanyPersistenceService implements TravelCompanyPersistence {

    private final TravelCompanyRepo travelCompanyRepo;

    @Override
    @Transactional
    public TravelCompany save(TravelCompany travelCompany) {
        log.debug("Saving travel company: {}", travelCompany.getCompanyName());
        return travelCompanyRepo.save(travelCompany);
    }

    @Override
    public TravelCompany findById(Integer id) {
        log.debug("Finding travel company by ID: {}", id);
        Optional<TravelCompany> company = travelCompanyRepo.findById(id);
        return company.orElse(null);
    }

    @Override
    public TravelCompany findByCompanyRegistrationNumber(String companyRegistrationNumber) {
        log.debug("Finding travel company by registration number: {}", companyRegistrationNumber);
        Optional<TravelCompany> company = travelCompanyRepo
                .findByCompanyRegistrationNumber(companyRegistrationNumber);
        return company.orElse(null);
    }
}