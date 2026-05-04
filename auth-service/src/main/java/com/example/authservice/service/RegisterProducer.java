package com.example.authservice.service;


import com.example.events.RegisterRequest;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
@Service
public class RegisterProducer {
    private final KafkaTemplate<String, RegisterRequest> kafkaTemplate;

    public RegisterProducer(KafkaTemplate<String, RegisterRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void send(RegisterRequest request){
        System.out.println("Sending to Kafka: " + request);
        kafkaTemplate.send("register",request);
    }
}
