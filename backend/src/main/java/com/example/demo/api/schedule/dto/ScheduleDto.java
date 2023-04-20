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
    private String time;
    private String category;
    private String memo;
}
