package com.busapp.user.service;

import com.busapp.user.entity.TravelCompany;

public interface TravelCompanys {

    TravelCompany addTravelCompany(TravelCompany travelCompany);

    TravelCompany getById(Integer id);

    TravelCompany getByCompanyRegistrationNumber(String registrationNumber);
}
