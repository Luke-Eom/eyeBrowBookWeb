package com.example.demo.controller;

import com.example.demo.entity.Schedule;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    // date별 스케쥴 find and sortByTime
    // GetMapping

    // create
    // if (time에 스케쥴이 == null) => exception
    public Schedule createSchedule() {

        return null;
    }
    // delete
    public String deleteSchedule() {

        if (scheduleService.checkSchedule == null) {
            response = "예약이 취소되었습니다";
        }

        return response;
    }
    // update
    // if (time에 스케쥴이 == null) => exception
    // else update

}
