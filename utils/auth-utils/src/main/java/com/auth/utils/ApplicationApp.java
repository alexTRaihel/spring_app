package com.auth.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value = "/test")
public class ApplicationApp {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationApp.class, args);
    }

    @GetMapping
    public String test(){
        return "Test";
    }
}
