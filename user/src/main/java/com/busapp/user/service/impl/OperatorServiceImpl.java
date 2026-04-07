package com.busapp.user.service.impl;

import com.busapp.user.entity.Operator;
import com.busapp.user.entity.TravelCompany;
import com.busapp.user.enums.Status;
import com.busapp.user.exception.DriverAlreadyExist;
import com.busapp.user.exception.GlobalExceptionEnums;
import com.busapp.user.model.OperatorRequest;
import com.busapp.user.model.OperatorResponse;
import com.busapp.user.service.OperatorService;
import com.busapp.user.service.TravelCompanys;
import com.busapp.user.service.persistence.OperatorPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OperatorServiceImpl implements OperatorService {

    private final OperatorPersistence operatorPersistence;
    private final TravelCompanys travelCompanys;

    @Override
    public OperatorResponse createOperator(OperatorRequest operator) {
        log.info("Creating operator for Aadhaar: {}", operator.getAdharCardNumber());
        Optional<Operator> operator1 = operatorPersistence.findByAdharCardNumber(operator.getAdharCardNumber());
        if (operator1.isPresent()) {
            throw new DriverAlreadyExist(GlobalExceptionEnums.DRIVER_ALREADY_EXIST, operator.getAdharCardNumber());
        }
        Operator busDriver = new Operator();
        busDriver.setCompanyId(operator.getCompanyId());
        busDriver.setAdharCardNumber(operator.getAdharCardNumber());
        busDriver.setUserId(operator.getUserId());
        busDriver.setYearsOfExperience(operator.getYearsOfExperience());
        busDriver.setDrivingLicenseNumber(operator.getDrivingLicenseNumber());
        busDriver.setDrivingLicenseImage(operator.getDrivingLicenseImage());
        busDriver.setAddress(operator.getAddress());
        busDriver.setStatus(Status.PENDING);
        busDriver.setCreatedAt(Instant.now());
        busDriver.setCreatedBy("USER");
        Operator savedOperator = operatorPersistence.save(busDriver);
        log.info("Operator created successfully with ID: {}", savedOperator.getId());
        TravelCompany company = travelCompanys.getById(savedOperator.getCompanyId());
        String companyName = company.getCompanyName();
        return OperatorResponse.builder()
                .companyName(companyName) // or fetch actual name
                .status(savedOperator.getStatus()).
                build();
    }

    @Override
    public Operator getDriverById(Integer id) {
        return operatorPersistence.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Driver not found with id: " + id));
    }

    @Override
    public List<Operator> getDriversByCompanyId(Integer operatorId) {
        List<Operator> drivers = operatorPersistence.findByCompanyId(operatorId);

        if (drivers.isEmpty()) {
            return List.of();
        }
        return drivers;
    }

    @Override
    public Operator getDriverByVehicleId(Integer vehicleId) {
        return operatorPersistence.findByVehicleId(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found with vehicleId: " + vehicleId));
    }
}
