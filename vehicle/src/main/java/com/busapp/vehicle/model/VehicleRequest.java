package com.busapp.vehicle.model;

import com.busapp.vehicle.enums.BusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {

    private int companyId;
    private String vehicleNumber;
    private String vehicleModel;
    private Integer totalSeats;
    private String vehicleColor;
    private String vehicleImage;
    private BusType busType;
}
