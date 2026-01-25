package com.busapp.schedule.entity;

import com.busapp.schedule.enums.Status;
import com.busapp.schedule.model.BaseCollection;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "schedules")
public class Schedule extends BaseCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int busId;
    private int routeId;
    private String arrivalTime;
    private String departureTime;
    private Status status;
}
