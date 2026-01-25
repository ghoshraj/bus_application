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
    private int CompanyId;

    @Column(nullable = false)
    @NotNull(message = "yearsOfExperience required")
    @Min(value = 4, message = "Minimum 4 years of experience is required")
    private Integer yearsOfExperience;

    @Column(nullable = false)
    @NotBlank(message = "Driving license number is required")
    @Size(min = 15, max = 16, message = "Driving license number must be between 15 and 16 characters")
    @Pattern(
            regexp = "^[A-Z]{2}[0-9]{2}[0-9]{4}[0-9]{7}$",
            message = "Invalid driving license number format"
    )
    private String drivingLicenseNumber;

    @Column(nullable = false)
    @NotBlank(message = "drivingLicenseImage is required")
    private String drivingLicenseImage;


    @Column(nullable = false)
    @NotBlank(message = "Aadhaar number is required")
    @Size(min = 12, max = 12, message = "Aadhaar number must be exactly 12 digits")
    @Pattern(regexp = "\\d{12}", message = "Aadhaar number must contain only digits")
    private String adharCardNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private Status status;

}
