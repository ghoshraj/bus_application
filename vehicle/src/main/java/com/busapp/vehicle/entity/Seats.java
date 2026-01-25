package com.busapp.vehicle.entity;

import com.busapp.vehicle.enums.Status;
import com.busapp.vehicle.model.BaseCollection;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "seats")
public class Seats extends BaseCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    private Vehicle vehicle;
    
    private String seatNumber;
    private String seatType;
    private Status status;
}
