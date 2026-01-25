package com.busapp.user.entity;

import com.busapp.user.enums.Status;
import com.busapp.user.model.BaseCollection;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "travel_companies")
@Data
public class TravelCompany extends BaseCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String companyName;
    private String companyOwnerName;
    private String contactEmail;
    private String contactPhone;
    private String gstNumber;
    private String companyRegistrationNumber;
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}
