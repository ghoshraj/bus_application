package com.busapp.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravellerProfileResponse {

    private String userName;
    private String phoneNumber;
    private LocalDate joiningDate;
    private int totalTrip;
    private long totalTravel;
    private Long walletBalance;
}
