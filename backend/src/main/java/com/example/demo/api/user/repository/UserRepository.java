package com.example.demo.api.user.repository;

import com.example.demo.api.user.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, String> {
    MyUser findByEmail(String email);
}
