package com.example.demo.api.user.dto;

import com.example.demo.oauth.entity.ProviderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private String username;
    private String profileImageUrl;
    private ProviderType providerType;
    private String roleCode;
}
