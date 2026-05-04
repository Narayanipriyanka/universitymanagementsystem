package com.example.studentservice.service;

import com.example.events.CourseEnrollEvent;
import com.example.studentservice.entity.Course;
import com.example.studentservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseConsumer {
    @Autowired
    private CourseRepository repository;
    @KafkaListener(topics="studentcourse",groupId="student-enroll-group")
    public void consume(CourseEnrollEvent event){

        for(UUID courseId:event.getCourseIds()){
            Course c=new Course();
            c.setStudentId(event.getStudentId());
            c.setCourseID(courseId);
            c.setSemester(event.getSemester());
            repository.save(c);
        }
    }
}
