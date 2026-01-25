package com.busapp.schedule.service.persistence.impl;

import com.busapp.schedule.entity.Schedule;
import com.busapp.schedule.repository.ScheduleRepository;
import com.busapp.schedule.service.persistence.SchedulePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchedulePersistenceImpl implements SchedulePersistence {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Optional<Schedule> getScheduleById(Integer id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public List<Schedule> getSchedulesByRouteId(Integer routeId) {
        return scheduleRepository.findByRouteId(routeId);
    }
}
