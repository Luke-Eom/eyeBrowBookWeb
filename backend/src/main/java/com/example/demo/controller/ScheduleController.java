package com.example.demo.controller;

import com.example.demo.entity.Schedule;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    ScheduleService sService;

    public ArrayList<Schedule> checkSchedule() {
        ArrayList<Schedule> allSchedule = sService.getAllSchedule();
        // dto 매핑
        return allSchedule;
    }

    // date별 스케쥴 find and sortByTime
    // GetMapping
    public Schedule checkUserSchedule(String username) {
        Schedule schedule = sService.getScheduleByUsername(username);
        // dto 매핑
        return schedule;
    }

    // create
    // if (time에 스케쥴이 == null) => exception
    public Schedule createSchedule() {

        return null;
    }
    // delete
//    public String deleteSchedule() {
//
//        if (sService.checkSchedule() == null) {
//            response = "예약이 취소되었습니다";
//        }
//
//        return response;
//    }
    // update
    // if (time에 스케쥴이 == null) => exception
    // else update

}
