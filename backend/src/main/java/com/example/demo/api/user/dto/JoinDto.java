package com.example.demo.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinDto {
    private String email;
    private String password;
    private String name;
    private MultipartFile image;
}
