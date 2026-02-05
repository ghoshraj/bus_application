package com.example.demo.entity;

import com.example.demo.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import static com.example.demo.constant.ModelConstants.*;

@Entity
@Data
@Table(name = USER_TABLE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String email;

    @NotNull
    private String password;

    private String phoneNumber;
    private String gender;
    private String isBus;
    private String isAdmin;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = USER_ROLES_TABLE,
            joinColumns = @JoinColumn(name = USER_ID))
    @Column(name = ROLE)
    private Set<Roles> roles = new HashSet<>();
}
