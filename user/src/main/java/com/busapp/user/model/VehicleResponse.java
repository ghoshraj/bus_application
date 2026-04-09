package com.busapp.user.model;

import com.busapp.user.enums.BusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponse {

    private String vehicleNumber;
    private String vehicleModel;
    private Integer totalSeats;
    private String vehicleColor;
    private String vehicleImage;
    private BusType busType;
}
