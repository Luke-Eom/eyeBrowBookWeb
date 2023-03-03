package com.example.demo.entity;

import lombok.Getter;

import javax.persistence.*;


@Entity
@Getter
@Table(name="categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 100)
    private String imgPath;


}
