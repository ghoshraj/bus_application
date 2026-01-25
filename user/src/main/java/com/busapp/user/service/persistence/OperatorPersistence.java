package com.busapp.user.service.persistence;

import com.busapp.user.entity.Operator;

import java.util.List;
import java.util.Optional;

public interface OperatorPersistence {

    Operator save(Operator operator);

    Optional<Operator> findById(Integer id);

    List<Operator> findByCompanyId(Integer operatorId);

    Optional<Operator>findByAdharCardNumber(String adharNumber);
    
    Optional<Operator> findByVehicleId(Integer vehicleId);
}

