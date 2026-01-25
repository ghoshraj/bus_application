package com.busapp.user.service;

import com.busapp.user.entity.Operator;

import java.util.List;

public interface OperatorService {

    Operator createOperator(Operator operator);

    Operator getDriverById(Integer id);

    List<Operator> getDriversByCompanyId(Integer operatorId);
    
    Operator getDriverByVehicleId(Integer vehicleId);
}
