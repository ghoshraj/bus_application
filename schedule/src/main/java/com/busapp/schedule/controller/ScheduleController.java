package com.busapp.schedule.controller;

import com.busapp.schedule.entity.Schedule;
import com.busapp.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@Valid @RequestBody Schedule schedule) {
        Schedule savedSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSchedule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Integer id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<Schedule>> getSchedulesByRouteId(@PathVariable Integer routeId) {
        List<Schedule> schedules = scheduleService.getSchedulesByRouteId(routeId);
        return ResponseEntity.ok(schedules);
    }

    @PostMapping("/{id}/assign-driver")
    public ResponseEntity<Schedule> assignDriverToSchedule(@PathVariable Integer id) {
        Schedule schedule = scheduleService.assignDriverToSchedule(id);
        return ResponseEntity.ok(schedule);
    }
}
