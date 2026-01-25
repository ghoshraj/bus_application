package com.busapp.booking.entity;

import com.busapp.booking.enums.Status;
import com.busapp.booking.model.BaseCollection;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "booking_details")
public class Booking extends BaseCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int travelerId;
    private int scheduleId;
    private double totalAmount;
    private String pnr;
    private Status status;
}
