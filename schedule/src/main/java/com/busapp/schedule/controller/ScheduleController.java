package com.busapp.schedule.controller;

import com.busapp.schedule.entity.Schedule;
import com.busapp.schedule.model.ErrorResponse;
import com.busapp.schedule.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@Tag(name = "Schedule APIs", description = "APIs related to bus schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Operation(summary = "Create schedule", description = "Create a new bus schedule for a route")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Schedule created successfully",
                    content = @Content(schema = @Schema(implementation = Schedule.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/apply")
    public ResponseEntity<Schedule> createSchedule(@Valid @RequestBody Schedule schedule) {
        Schedule savedSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSchedule);
    }

    @Operation(summary = "Get schedule by ID", description = "Fetch schedule details using schedule ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Schedule found",
                    content = @Content(schema = @Schema(implementation = Schedule.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Integer id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    @Operation(summary = "Get schedules by route ID", description = "Fetch all schedules for a given route")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Schedules fetched successfully",
                    content = @Content(array = @io.swagger.v3.oas.annotations.media.ArraySchema
                            (schema = @Schema(implementation = Schedule.class)))),
            @ApiResponse(responseCode = "404", description = "Route not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<Schedule>> getSchedulesByRouteId(@PathVariable Integer routeId) {
        List<Schedule> schedules = scheduleService.getSchedulesByRouteId(routeId);
        return ResponseEntity.ok(schedules);
    }

    @Operation(summary = "Assign driver to schedule", description = "Assign an available driver to a specific schedule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Driver assigned successfully",
                    content = @Content(schema = @Schema(implementation = Schedule.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{id}/assign-driver")
    public ResponseEntity<Schedule> assignDriverToSchedule(@PathVariable Integer id) {
        Schedule schedule = scheduleService.assignDriverToSchedule(id);
        return ResponseEntity.ok(schedule);
    }
}
