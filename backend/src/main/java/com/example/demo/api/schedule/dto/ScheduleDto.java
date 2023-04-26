package com.example.demo.api.schedule.dto;

import com.example.demo.api.user.dto.UserDto;
import com.example.demo.oauth.entity.ProviderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    private String username;

    // 연도 - 날짜 형식 확인 및 히스토리 관리 확인
    private String time;
    private String category;
    private String memo;
}
