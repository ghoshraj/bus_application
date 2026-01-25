package com.busapp.user.service.persistence.impl;

import com.busapp.user.entity.Operator;
import com.busapp.user.repository.OperatorRepository;
import com.busapp.user.service.persistence.OperatorPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperatorPersistenceServiceImpl implements OperatorPersistence {

    private final OperatorRepository operatorRepository;

    @Override
    public Operator save(Operator operator) {
        return operatorRepository.save(operator);
    }

    @Override
    public Optional<Operator> findById(Integer id) {
        return operatorRepository.findById(id);
    }

    @Override
    public List<Operator> findByCompanyId(Integer operatorId) {
        return operatorRepository.findByUserId(operatorId);
    }

    @Override
    public Optional<Operator> findByAdharCardNumber(String adharNumber) {
        return operatorRepository.findByAdharCardNumber(adharNumber);
    }

    @Override
    public Optional<Operator> findByVehicleId(Integer vehicleId) {
        return operatorRepository.findByVehicleId(vehicleId);
    }
}
