package com.example.demo.api.schedule.repository;

import com.example.demo.api.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findByUsername(String username);

}
