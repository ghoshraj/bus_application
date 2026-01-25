package com.busapp.vehicle.entity;

import com.busapp.vehicle.enums.Status;
import com.busapp.vehicle.model.BaseCollection;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "vehicles")
public class Vehicle extends BaseCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int companyId;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleModel;
    private String totalSeats;
    private String vehicleColor;
    private String vehicleImage;
    private Status status;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seats> seats;
}
