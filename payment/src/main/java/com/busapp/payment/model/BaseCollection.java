package com.busapp.payment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaseCollection {

    private String createdBy;
    @CreatedDate
    private Instant createdAt;
    private String updatedBy;
    @LastModifiedDate
    private Instant updatedAt;
}
