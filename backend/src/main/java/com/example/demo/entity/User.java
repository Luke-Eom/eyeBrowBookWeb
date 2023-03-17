package com.example.demo.entity;

import com.example.demo.enums.Roles;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "members")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String password;


    @Column(length = 50, nullable=false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Roles role;

}
