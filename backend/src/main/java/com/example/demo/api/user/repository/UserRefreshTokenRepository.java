package com.example.demo.api.user.repository;

import com.example.demo.api.user.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {

    UserRefreshToken findByEmail(String email);
    UserRefreshToken findByEmailAndRefreshToken(String email, String refreshToken);

}
