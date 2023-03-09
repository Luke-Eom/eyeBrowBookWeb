package com.example.demo.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    private String imgPath;

    @ManyToOne
    private Member member;

}
