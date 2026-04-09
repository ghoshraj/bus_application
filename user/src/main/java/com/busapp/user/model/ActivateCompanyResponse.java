package com.busapp.user.model;

import com.busapp.user.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ActivateCompanyResponse {

    private String companyName;
    private String companyOwnerName;
    private String contactEmail;
    private String contactPhone;
    private Address address;
    private List<VehicleResponse> vehicle;
}
