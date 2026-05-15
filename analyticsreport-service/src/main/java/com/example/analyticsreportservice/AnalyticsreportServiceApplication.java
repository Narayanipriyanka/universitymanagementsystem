package com.example.analyticsreportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class AnalyticsreportServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticsreportServiceApplication.class, args);
    }

}
