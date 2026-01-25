package com.busapp.schedule.service;

import com.busapp.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    Schedule createSchedule(Schedule schedule);
    
    Schedule getScheduleById(Integer id);
    
    List<Schedule> getSchedulesByRouteId(Integer routeId);
    
    Schedule assignDriverToSchedule(Integer scheduleId);
}
