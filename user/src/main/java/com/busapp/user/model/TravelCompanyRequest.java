package com.busapp.user.model;

import com.busapp.user.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelCompanyRequest {

    private String companyName;
    private String companyOwnerName;
    private String contactEmail;
    private String contactPhone;
    private String gstNumber;
    private String companyRegistrationNumber;
    private Address address;
}
