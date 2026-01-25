package com.busapp.seatinventory.entity;

import com.busapp.seatinventory.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "seats")
public class Seats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int busId;

    private String seatNumber;
    private String seatType;
    private Status status;
}
