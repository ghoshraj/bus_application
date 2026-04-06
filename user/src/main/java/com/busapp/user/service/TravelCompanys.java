package com.busapp.user.service;

import com.busapp.user.entity.TravelCompany;
import com.busapp.user.model.TravelCompanyRequest;

public interface TravelCompanys {

    TravelCompany addTravelCompany(TravelCompanyRequest travelCompany);

    TravelCompany getById(Integer id);

    TravelCompany getByCompanyRegistrationNumber(String registrationNumber);
}
