package com.busapp.user.entity;

import com.busapp.user.model.BaseCollection;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "traveller_profiles")
public class TravellerProfiles extends BaseCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int userId;
    private Long walletBalance;

}
