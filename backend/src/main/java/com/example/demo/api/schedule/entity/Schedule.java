package com.example.demo.api.schedule.entity;

import com.example.demo.api.user.entity.MyUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

// props: seq, user, time, cat
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Schedule")
public class Schedule {

    @JsonIgnore
    @Id
    @Column(name = "SCHEDULE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long scheduleSeq;

    @Column(name = "USER")
    @NotNull
    private MyUser user;

    @Column(name = "TIME")
    @NotNull
    private LocalDateTime time;

    @Column(name = "CATEGORY")
    @NotNull
    private String category;

    @Column(name = "MEMO")
    private String memo;

}
