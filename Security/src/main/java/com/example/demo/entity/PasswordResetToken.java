package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import static com.example.demo.constant.ModelConstants.PASSWORD_RESET;

@Data
@Entity
@Table(name = PASSWORD_RESET)
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int userId;
    @Column(unique = true)
    private String token;
    private LocalDateTime expiryDate;
    private boolean used;
}
