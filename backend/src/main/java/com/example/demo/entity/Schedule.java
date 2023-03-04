package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Schedule {

    @Id
    private int id;

    private LocalDateTime date;

    private long time;

    private Member member;

    // history dto에서 만들기
}
