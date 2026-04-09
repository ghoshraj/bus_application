package com.busapp.user.model;

import com.busapp.user.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TravelCompanyResponse {

    private int companyId;
    private Status Companystatus;

}
