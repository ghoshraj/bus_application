package com.busapp.user.model;

import com.busapp.user.entity.Address;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelCompanyRequest {

    @NotBlank(message = "Company name is required")
    private String companyName;
    @NotBlank(message = "Company owner name is required")
    private String companyOwnerName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String contactEmail;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String contactPhone;
    @NotBlank(message = "GST number is required")
    @Pattern(regexp = "^[0-9A-Z]{15}$", message = "Invalid GST number format")
    private String gstNumber;
    @NotBlank(message = "Company registration number is required")
    @Size(min = 12, max = 12, message = "Registration number must be 12 digits")
    @Pattern(regexp = "^[0-9]{12}$", message = "Registration number must contain only digits")
    private String companyRegistrationNumber;
    @NotNull(message = "Address is required")
    private Address address;
}
