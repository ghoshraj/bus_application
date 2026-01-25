package com.busapp.payment.entity;

import com.busapp.payment.enums.Status;
import com.busapp.payment.model.BaseCollection;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "payments")
public class Payment extends BaseCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int bookingId;
    private String paymentMethod;
    private String transactionId;
    private Double amount;
    private Status status;

}
