package com.busapp.vehicle.entity;

import com.busapp.vehicle.enums.BookingStatus;
import com.busapp.vehicle.enums.BusType;
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
    private String vehicleModel;
    private Integer totalSeats;
    private String vehicleColor;
    private String vehicleImage;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    @Enumerated(EnumType.STRING)
    private BusType busType;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seats> seats;
}
