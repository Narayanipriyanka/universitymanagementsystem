package com.example.enrollmentservice.service;

import com.example.enrollmentservice.entity.Student;
import com.example.enrollmentservice.repository.StudentRepository;
import com.example.events.MaterialEvent;
import com.example.events.StudentCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentConsumer {
    @Autowired
    private StudentRepository studentRepository;
    @KafkaListener(topics="sendStudentCreated",groupId="enroll-student-group")
    public void consume(StudentCreatedEvent event){
        Student s=new Student();
        s.setEmail(event.getEmail());
        s.setPassword(event.getPassword());
        s.setStudentStatus(event.getStudentStatus());
        s.setUsername(event.getUsername());
        studentRepository.save(s);

    }
}
