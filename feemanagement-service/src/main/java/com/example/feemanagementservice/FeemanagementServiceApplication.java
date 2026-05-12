package com.example.feemanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class FeemanagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeemanagementServiceApplication.class, args);
    }

}
