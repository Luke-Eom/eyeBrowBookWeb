package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
//    Member findByEmailAndPassword(String email, String password);
    Member findByEmail(String email);

    Member save(String email, String nickname);

}
