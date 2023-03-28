package com.example.demo.service;

import com.example.demo.entity.Schedule;
import com.example.demo.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository sRepo;

    @Transactional
    public ArrayList<Schedule> getAllSchedule() {
        ArrayList<Schedule> allSchedule = (ArrayList<Schedule>) sRepo.findAll();
        // dto 매핑
        return allSchedule;
    }

    @Transactional
    public Schedule getScheduleByUsername (String username) {
        Schedule schedule = sRepo.findByUsername(username);
        // update required
        return schedule;
    }
}
