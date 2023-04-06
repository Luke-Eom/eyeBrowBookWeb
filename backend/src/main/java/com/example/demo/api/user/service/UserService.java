package com.example.demo.api.user.service;

import com.example.demo.api.user.dto.JoinDto;
import com.example.demo.api.user.dto.UserDto;

public interface UserService {

    public UserDto getUser(String userEmail);

    public void joinUser(JoinDto joinDto);

}
