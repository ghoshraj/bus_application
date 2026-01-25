package com.busapp.schedule.service.persistence;

import com.busapp.schedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface SchedulePersistence {

    Schedule saveSchedule(Schedule schedule);
    
    Optional<Schedule> getScheduleById(Integer id);
    
    List<Schedule> getSchedulesByRouteId(Integer routeId);
}
