package com.example.demo.service;

import com.example.demo.entity.Schedule;

import java.util.ArrayList;

public interface ScheduleService {
    ArrayList<Schedule> getAllSchedule();

    Schedule getScheduleByUsername (String username);

}
