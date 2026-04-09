package com.busapp.user.mapper;

import com.busapp.user.entity.Address;
import com.busapp.user.entity.TravelCompany;
import com.busapp.user.enums.Status;
import com.busapp.user.model.ActivateCompanyResponse;
import com.busapp.user.model.TravelCompanyRequest;
import com.busapp.user.model.VehicleResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class TravelCompanysMapper {

    public TravelCompany toCreateCompany(TravelCompanyRequest travelCompany) {
        TravelCompany existingCompany = new TravelCompany();
        existingCompany.setCompanyName(travelCompany.getCompanyName());
        existingCompany.setCompanyOwnerName(travelCompany.getCompanyOwnerName());
        existingCompany.setCompanyRegistrationNumber(travelCompany.getCompanyRegistrationNumber());
        existingCompany.setContactEmail(travelCompany.getContactEmail());
        existingCompany.setContactPhone(travelCompany.getContactPhone());
        existingCompany.setGstNumber(travelCompany.getGstNumber());
        existingCompany.setCreatedAt(Instant.now());
        Address addressRequest = travelCompany.getAddress();
        Address address = new Address();
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setPinCode(addressRequest.getPinCode());
        existingCompany.setAddress(address);
        existingCompany.setCreatedBy("USER");
        existingCompany.setStatus(Status.PENDING);

        return existingCompany;
    }

    public ActivateCompanyResponse toResponse(TravelCompany travelCompany, List<VehicleResponse> vehicles){
        return ActivateCompanyResponse.builder()
                .companyName(travelCompany.getCompanyName())
                .companyOwnerName(travelCompany.getCompanyOwnerName())
                .contactEmail(travelCompany.getContactEmail())
                .contactPhone(travelCompany.getContactPhone())
                .address(travelCompany.getAddress())
                .vehicle(vehicles)
                .build();
    }
}
