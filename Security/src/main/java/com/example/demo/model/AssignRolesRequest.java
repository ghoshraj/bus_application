package com.example.demo.model;

import com.example.demo.enums.Roles;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class AssignRolesRequest {

    @NotNull(message = "userId is required")
    private Integer userId;

    @NotEmpty(message = "At least one role must be assigned")
    private Set<Roles> roles;
}
