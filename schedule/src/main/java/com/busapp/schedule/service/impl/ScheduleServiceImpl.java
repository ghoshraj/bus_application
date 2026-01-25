package com.busapp.schedule.service.impl;

import com.busapp.schedule.entity.Schedule;
import com.busapp.schedule.entity.ScheduleDriver;
import com.busapp.schedule.enums.Status;
import com.busapp.schedule.exception.DriverNotFound;
import com.busapp.schedule.exception.GlobalExceptionEnums;
import com.busapp.schedule.exception.ScheduleNotFound;
import com.busapp.schedule.model.OperatorResponse;
import com.busapp.schedule.repository.ScheduleDriverRepository;
import com.busapp.schedule.service.ScheduleService;
import com.busapp.schedule.service.client.UserServiceClient;
import com.busapp.schedule.service.persistence.SchedulePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final SchedulePersistence schedulePersistence;
    private final ScheduleDriverRepository scheduleDriverRepository;
    private final UserServiceClient userServiceClient;

    @Override
    @Transactional
    public Schedule createSchedule(Schedule schedule) {
        if (schedule.getStatus() == null) {
            schedule.setStatus(Status.ACTIVE);
        }
        schedule.setCreatedAt(Instant.now());
        schedule.setCreatedBy("");
        
        return schedulePersistence.saveSchedule(schedule);
    }

    @Override
    public Schedule getScheduleById(Integer id) {
        return schedulePersistence.getScheduleById(id)
                .orElseThrow(() -> new ScheduleNotFound(GlobalExceptionEnums.SCHEDULE_NOT_FOUND, id));
    }

    @Override
    public List<Schedule> getSchedulesByRouteId(Integer routeId) {
        return schedulePersistence.getSchedulesByRouteId(routeId);
    }

    @Override
    @Transactional
    public Schedule assignDriverToSchedule(Integer scheduleId) {
        // Get schedule by id
        Schedule schedule = getScheduleById(scheduleId);
        
        // Get busId from schedule
        Integer busId = schedule.getBusId();
        
        // Call user service to get operator by vehicleId (busId)
        OperatorResponse operator = userServiceClient.getOperatorByVehicleId(busId)
                .block(); // Blocking call - in production consider using reactive approach
        
        if (operator == null || operator.getId() == null) {
            throw new DriverNotFound(GlobalExceptionEnums.DRIVER_NOT_FOUND, busId);
        }
        
        Integer driverId = operator.getId();
        
        // Check if schedule-driver mapping already exists
        Optional<ScheduleDriver> existingMapping = scheduleDriverRepository.findByScheduleId(scheduleId);
        
        ScheduleDriver scheduleDriver;
        if (existingMapping.isPresent()) {
            // Update existing mapping
            scheduleDriver = existingMapping.get();
            scheduleDriver.setDriverId(driverId);
        } else {
            // Create new mapping
            scheduleDriver = new ScheduleDriver();
            scheduleDriver.setScheduleId(scheduleId);
            scheduleDriver.setDriverId(driverId);
        }
        
        scheduleDriverRepository.save(scheduleDriver);
        
        return schedule;
    }
}
