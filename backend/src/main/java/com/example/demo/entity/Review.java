package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Review {

    @Id
    private int id;

    private String title;

    private String content;

    private String imgPath;

    private Member member;

}
