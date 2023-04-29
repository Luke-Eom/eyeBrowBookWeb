package com.example.demo.api.schedule.controller;

import com.example.demo.api.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    //스케쥴 조회 (전체 조회,아이디별 조회/ 스케쥴 생성 / 스케쥴 수정: 고객 요청 - 관리자 확인 - 수정완료 / 스케쥴 삭제: 고객 요청 - 관리자 확인 - 삭제 완료 )

    // ApiResponse로 받아오기?


}
