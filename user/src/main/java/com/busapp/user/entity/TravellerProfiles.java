package com.busapp.user.entity;

import com.busapp.user.enums.Gender;
import com.busapp.user.model.BaseCollection;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "traveller_profiles")
public class TravellerProfiles extends BaseCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int userId;
    private String name;
    private String phoneNumber;
    private LocalDate joiningDate;
    private int totalTrip;
    private long totalTravel;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Long walletBalance;

}
