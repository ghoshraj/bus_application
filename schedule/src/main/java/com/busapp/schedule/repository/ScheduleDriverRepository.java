package com.busapp.schedule.repository;

import com.busapp.schedule.entity.ScheduleDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleDriverRepository extends JpaRepository<ScheduleDriver, Integer> {

    Optional<ScheduleDriver> findByScheduleId(Integer scheduleId);
}
