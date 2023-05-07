package com.example.demo.api.schedule.service;

import com.example.demo.api.schedule.dto.ScheduleDto;
import com.example.demo.api.schedule.entity.Schedule;
import com.example.demo.api.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepo;

    // 전체 조회,아이디별 조회/ 스케쥴 생성 / 스케쥴 수정 / 스케쥴 삭제

    // 전체 스케쥴 조회
    public ArrayList<ScheduleDto> getAllSchedules() {
        ArrayList<ScheduleDto> scheduleDtoList = new ArrayList<>();
        ArrayList<Schedule> scheduleList = (ArrayList<Schedule>) scheduleRepo.findAll();
//
//        for() {
//
//        }


        return scheduleDtoList;
    }

    public Schedule getScheduleByUsername(String username) {
        // contextHolder에서 .getContext().getAuthentication.getName();

        return scheduleRepo.findByUsername(username);

    }

    public void createSchedule(Schedule schedule) {
        scheduleRepo.save(schedule);

        // 지정 기간 내에 예약 있을 시 확인 메시지 -> service part

    }

    public void updateSchedule(Schedule schedule) {
        // service 단에서 this.xxx = xxx;


    }

    public void deleteSchedule(Schedule schedule) {

    }


}
