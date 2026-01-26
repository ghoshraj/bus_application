package com.example.demo.model;

import com.example.demo.enums.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @Schema(example = "1")
    private Integer id;

    @Schema(example = "Raj")
    private String name;

    @Schema(example = "raj@example.com")
    private String email;

    @Schema(example = "9876543210")
    private String phoneNumber;

    @Schema(example = "MALE")
    private String gender;

    @Schema(description = "Assigned roles")
    private Set<Roles> roles;
}

