package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
//    Member findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    User save(String email, String nickname);

    Optional<User> findByNickname(String username);
}
