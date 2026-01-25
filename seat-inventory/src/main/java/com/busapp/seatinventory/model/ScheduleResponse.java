package com.busapp.seatinventory.model;

import lombok.Data;

@Data
public class ScheduleResponse {
    private Integer id;
    private Integer busId;
    private Integer routeId;
    private String arrivalTime;
    private String departureTime;
}
