package com.example.demo.model;

import com.example.demo.enums.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static com.example.demo.constant.ModelConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @Schema(example = USER_ID_EXAMPLE)
    private Integer id;

    @Schema(example = USERNAME_EXAMPLE)
    private String name;

    @Schema(example = USER_EMAIL_EXAMPLE)
    private String email;

    @Schema(example = USER_PHONE_EXAMPLE)
    private String phoneNumber;

    @Schema(example = USER_GENDER_EXAMPLE)
    private String gender;

    @Schema(description = USER_ROLES_DESC)
    private Set<Roles> roles;
}

