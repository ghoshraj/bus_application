package com.busapp.user.repository;

import com.busapp.user.entity.TravelCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelCompanyRepo extends JpaRepository<TravelCompany, Integer> {

    Optional<TravelCompany> findByCompanyRegistrationNumber(String companyRegistrationNumber);

}
