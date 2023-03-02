package com.example.demo.controller;

import com.example.demo.entity.Categories;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class CategoryController {

    @Autowired
    CategoryRepository catRepo;

    @GetMapping("/cats")
    public List<Categories> getItems() {
        List<Categories> cats = catRepo.findAll();
        return cats;
    }
}
