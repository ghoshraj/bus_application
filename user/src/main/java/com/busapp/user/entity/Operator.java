package com.busapp.user.entity;

import com.busapp.user.enums.Status;
import com.busapp.user.model.BaseCollection;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "bus_operators")
public class Operator extends BaseCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int userId;
    private int vehicleId;
    private int companyId;

    @Column(nullable = false)
    private Integer yearsOfExperience;
    @Column(nullable = false)
    private String drivingLicenseNumber;
    @Column(nullable = false)
    private String drivingLicenseImage;
    @Column(nullable = false)
    private String adharCardNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @Enumerated(EnumType.STRING)
    private Status status;

}
