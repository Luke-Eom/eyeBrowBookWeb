package com.example.demo.api.user.service;

import com.example.demo.api.user.dto.JoinDto;
import com.example.demo.api.user.dto.UserDto;
import com.example.demo.api.user.entity.MyUser;
import com.example.demo.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    private final PasswordEncoder pwEncoder;

    public UserDto getUser(String userEmail) {

        return userRepo.findByEmail(userEmail).toDTO();

    }

    @Transactional
    public void joinUser(JoinDto joinDto) {

        LocalDateTime now = LocalDateTime.now();
        joinDto.setPassword(pwEncoder.encode(joinDto.getPassword()));
        MultipartFile mpFile = joinDto.getImage();
        String contentType = mpFile.getContentType();
        String originalFileExtension = null;

        if(contentType.contains("image/jpeg")) {
            originalFileExtension = ".jpg";
        } else if(contentType.contains("image/png")) {
            originalFileExtension = ".png";
        }

        File convertFile = new File(joinDto.getName() + "" +originalFileExtension);

        try {
            if(convertFile.createNewFile()) {
                try(FileOutputStream fos = new FileOutputStream(convertFile)) {
                    fos.write(mpFile.getBytes());
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        MyUser myUser = new MyUser(joinDto, now, now, joinDto.getName() + "" + originalFileExtension);
        userRepo.saveAndFlush(myUser);


    }


}
