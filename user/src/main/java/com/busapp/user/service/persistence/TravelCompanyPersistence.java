package com.busapp.user.service.persistence;

import com.busapp.user.entity.TravelCompany;

public interface TravelCompanyPersistence {

    TravelCompany save(TravelCompany travelCompany);

    TravelCompany findByCompanyRegistrationNumber(String companyRegistrationNumber);

    TravelCompany findById(Integer id);

}
