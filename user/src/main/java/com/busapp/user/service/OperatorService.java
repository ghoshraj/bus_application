package com.busapp.user.service;

import com.busapp.user.entity.Operator;
import com.busapp.user.model.OperatorRequest;
import com.busapp.user.model.OperatorResponse;

import java.util.List;

public interface OperatorService {

    OperatorResponse createOperator(OperatorRequest operator);

    Operator getDriverById(Integer id);

    List<Operator> getDriversByCompanyId(Integer operatorId);
    
    Operator getDriverByVehicleId(Integer vehicleId);
}
