package com.example.studentservice.service;


import com.example.events.RegisterRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StudentConsumer {

    private final StudentService studentService;
    public StudentConsumer(StudentService studentService) {
        this.studentService = studentService;
    }
@KafkaListener(topics="register",groupId="student-group")
    public void consume(RegisterRequest request){
    if(request == null){
        System.out.println("Request NULL");
        return;
    }  System.out.println("ROLE = " + request.getRole());
    System.out.println("Received: " + request.getFirstName());
    if("STUDENT".equalsIgnoreCase(request.getRole())){ System.out.println(" Saving student...");
        studentService.registerStudent(request);
        }else
        {
        System.out.println(" Role error");
    }
}
}
