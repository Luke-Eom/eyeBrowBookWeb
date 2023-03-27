package com.example.demo.service;

import com.example.demo.entity.Schedule;
import com.example.demo.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository sRepo;

    @Transactional
    public Schedule getScheduleByUsername (String username) {
        Schedule schedule = sRepo.findByUsername(username);

        return schedule;
    }
}
