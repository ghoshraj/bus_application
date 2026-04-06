package com.busapp.vehicle.entity;

import com.busapp.vehicle.enums.BookingStatus;
import com.busapp.vehicle.enums.SeatType;
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
    private Double price;
    @Column(nullable = false)
    private String seatNumber;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}
