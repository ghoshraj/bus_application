package com.busapp.seatinventory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "seat_locks")
public class SeatLock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer busId;
    private String seatNumber;
    private Instant lockedAt;
    private Instant expiresAt;
    private Boolean isActive;
}
