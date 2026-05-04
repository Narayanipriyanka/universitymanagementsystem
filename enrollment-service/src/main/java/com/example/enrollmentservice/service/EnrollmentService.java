package com.example.enrollmentservice.service;

import com.example.enrollmentservice.dto.EnrollDTO;
import com.example.enrollmentservice.entity.Enrollment;
import com.example.enrollmentservice.repository.EnrollnmentRepository;
import com.example.events.CourseEnrollEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnrollmentService {
@Autowired
    private EnrollnmentRepository enrollnmentRepository;
private final KafkaTemplate<String,Object> kafkaTemplate;

    public EnrollmentService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String enrollInCourse(EnrollDTO dto){
    Enrollment e=new Enrollment();
    e.setCourseCode(dto.getCourseCode());
    e.setSem(dto.getSem());
    List<UUID> ids=e.getStudentid();
    ids.add(dto.getStudentId());
    e.setStudentid(ids);
    enrollnmentRepository.save(e);
        CourseEnrollEvent dtos=new CourseEnrollEvent(e.getCourseCode(),e.getSem(),e.getStudentid());
    kafkaTemplate.send("studentenrolls",dtos);
    return "enrolled successfully";
}

}
