package com.busapp.schedule.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "schedule_driver")
public class ScheduleDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int scheduleId;
    private int driverId;
}
