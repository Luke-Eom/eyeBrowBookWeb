package com.example.demo.api.user.controller;

import com.example.demo.api.user.dto.UserDto;
import com.example.demo.api.user.entity.MyUser;
import com.example.demo.api.user.service.UserService;
import com.example.demo.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/my")
    public ApiResponse<UserDto> getUser(MyUser myUser) {

        UserDto user = userService.getUser(myUser.getEmail());

        return ApiResponse.success("user", user);

    }

}
