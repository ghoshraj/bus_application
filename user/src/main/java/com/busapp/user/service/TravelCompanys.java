package com.busapp.user.service;

import com.busapp.user.entity.TravelCompany;
import com.busapp.user.model.TravelCompanyRequest;
import com.busapp.user.model.TravelCompanyResponse;

public interface TravelCompanys {

    TravelCompanyResponse addTravelCompany(TravelCompanyRequest travelCompany, String token);

    TravelCompany getById(Integer id);

    TravelCompany getByCompanyRegistrationNumber(String registrationNumber);
}
