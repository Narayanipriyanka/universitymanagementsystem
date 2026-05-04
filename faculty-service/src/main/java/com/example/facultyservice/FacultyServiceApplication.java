package com.example.facultyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class FacultyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacultyServiceApplication.class, args);
    }

}
