package com.busapp.user.service.impl;

import com.busapp.user.entity.Operator;
import com.busapp.user.service.OperatorService;
import com.busapp.user.service.persistence.OperatorPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OperatorServiceImpl implements OperatorService {

    private final OperatorPersistence operatorPersistence;

    @Override
    public Operator createOperator(Operator operator) {
        Optional<Operator> operator1 = operatorPersistence.findByAdharCardNumber(operator.getAdharCardNumber());
        if (operator1.isEmpty()){
            return operatorPersistence.save(operator);
        }
        else {
            throw new RuntimeException("Driver already register with this adharNumber : " + operator.getAdharCardNumber());
        }
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
