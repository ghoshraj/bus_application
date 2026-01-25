package com.busapp.user.repository;

import com.busapp.user.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer> {

    List<Operator> findByUserId(Integer operatorId);

    Optional<Operator> findByAdharCardNumber(String adharNumber);
    
    Optional<Operator> findByVehicleId(Integer vehicleId);
}
