package com.example.demo.oauth.service;

import com.example.demo.api.user.entity.MyUser;
import com.example.demo.api.user.repository.UserRepository;
import com.example.demo.oauth.entity.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final UserRepository userRepo;

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {

        MyUser user = userRepo.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("존재하지않습니다");
        }

        return new MyUserDetails(user);

    }

}
