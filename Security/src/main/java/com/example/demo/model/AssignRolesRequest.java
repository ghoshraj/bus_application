package com.example.demo.model;

import com.example.demo.enums.Roles;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

import static com.example.demo.constant.ModelConstants.ROLE_REQUIRED;
import static com.example.demo.constant.ModelConstants.USER_ID_REQUIRED;

@Data
public class AssignRolesRequest {

    @NotNull(message = USER_ID_REQUIRED)
    private Integer userId;

    @NotEmpty(message = ROLE_REQUIRED)
    private Set<Roles> roles;
}
