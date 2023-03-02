package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class categoryController {

    @GetMapping("/items")
    public List<String> getItems() {
        List<String> items = new ArrayList<>();
        items.add("tattoo");
        items.add("retouch");
        items.add("care");

        return items;
    }
}
