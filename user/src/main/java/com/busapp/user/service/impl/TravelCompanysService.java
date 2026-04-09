package com.busapp.user.service.impl;

import com.busapp.user.entity.TravelCompany;
import com.busapp.user.enums.Status;
import com.busapp.user.exception.BusinessException;
import com.busapp.user.exception.GlobalExceptionEnums;
import com.busapp.user.mapper.TravelCompanysMapper;
import com.busapp.user.messagebroker.client.VehicleClient;
import com.busapp.user.model.ActivateCompanyResponse;
import com.busapp.user.model.TravelCompanyRequest;
import com.busapp.user.model.TravelCompanyResponse;
import com.busapp.user.model.VehicleResponse;
import com.busapp.user.service.TravelCompanys;
import com.busapp.user.service.persistence.OperatorPersistence;
import com.busapp.user.service.persistence.TravelCompanyPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelCompanysService implements TravelCompanys {

    private final TravelCompanyPersistence travelCompanyPersistence;
    private final TravelCompanysMapper travelCompanysMapper;
    private final OperatorPersistence operatorPersistence;
    private final VehicleClient vehicleClient;

    @Override
    @Transactional
    public TravelCompanyResponse addTravelCompany(TravelCompanyRequest travelCompany) {
        log.info("Attempting to add travel company with registration number: {}", travelCompany.getCompanyRegistrationNumber());
        // Check if company with same registration number already exists
        TravelCompany existingCompany = travelCompanyPersistence
                .findByCompanyRegistrationNumber(travelCompany.getCompanyRegistrationNumber());
        if (existingCompany != null) {
            log.warn("Travel company already exists with registration number: {}", travelCompany.getCompanyRegistrationNumber());
            throw new BusinessException(GlobalExceptionEnums.COMPANY_ALREADY_EXIST,
                    travelCompany.getCompanyRegistrationNumber());
        }
        TravelCompany company = travelCompanysMapper.toCreateCompany(travelCompany);
        TravelCompany savedCompany = travelCompanyPersistence.save(company);
        log.info("Successfully added travel company with ID: {}", savedCompany.getId());

        return TravelCompanyResponse.builder()
                .companyId(savedCompany.getId())
                .Companystatus(savedCompany.getStatus())
                .build();
    }

    @Override
    public ActivateCompanyResponse activateCompany(int companyId, String token) {
        log.info("Starting activation process for companyId: {}", companyId);
        //minimum required vehicle should be 2 and operator should be 6.
        TravelCompany travelCompany = getById(companyId);
        travelCompany.setStatus(Status.ACTIVE);
        travelCompanyPersistence.save(travelCompany);
        long driverCount = operatorPersistence.getDriverCount(companyId);
        List<VehicleResponse> vehicles = vehicleClient.getVehicle(companyId, token);
        long vehicleCount = vehicles.size();
        long requiredDrivers = vehicleCount * 3;
        if (vehicleCount < 2) {
            throw new BusinessException(GlobalExceptionEnums.COMPANY_RESOURCE_REQUIREMENT_NOT_MET, vehicleCount);
        }
        if (requiredDrivers < driverCount) {
            throw new BusinessException(GlobalExceptionEnums.COMPANY_DRIVER_REQUIREMENT_NOT_MET, driverCount);
        }
        log.info("Company activated successfully for companyId: {}", companyId);
        return travelCompanysMapper.toResponse(travelCompany, vehicles);
    }

    @Override
    public TravelCompany getById(Integer id) {
        log.info("Fetching travel company by ID: {}", id);
        TravelCompany company = travelCompanyPersistence.findById(id);
        if (company == null) {
            log.warn("Travel company not found with ID: {}", id);
            throw new BusinessException(GlobalExceptionEnums.COMPANY_NOT_FOUND, String.valueOf(id));
        }
        log.info("Successfully retrieved travel company with ID: {}", id);
        return company;
    }

    @Override
    public TravelCompany getByCompanyRegistrationNumber(String registrationNumber) {
        log.info("Fetching travel company by registration number: {}", registrationNumber);
        TravelCompany company = travelCompanyPersistence
                .findByCompanyRegistrationNumber(registrationNumber.trim());
        if (company == null) {
            log.warn("Travel company not found with registration number: {}", registrationNumber);
            throw new BusinessException(GlobalExceptionEnums.COMPANY_NOT_FOUND, registrationNumber);
        }
        log.info("Successfully retrieved travel company with registration number: {}", registrationNumber);
        return company;
    }
}