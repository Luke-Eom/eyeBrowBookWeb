package com.example.demo.api.schedule.dto;

import com.example.demo.api.user.dto.UserDto;
import com.example.demo.oauth.entity.ProviderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    // OAuth2 토큰으로 받아올때 username그대로 받는지 확인
    private String username;

    // 연도 - 날짜 형식 확인 및 히스토리 관리 확인
    private LocalDateTime time;
    private String category;
    private String memo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


}
