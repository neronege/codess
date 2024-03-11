package com.exam.taskNew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.exam.taskNew")
public class CompanytaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanytaskApplication.class, args);
    }
}