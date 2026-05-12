package com.example.examservice.service;

import com.example.events.ExamFeePaidEvent;
import com.example.examservice.entity.ExamFeeDetails;
import com.example.examservice.repository.ExamFeeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ExamFeeDetailsConsumer {
    @Autowired
    private ExamFeeDetailsRepository examFeeDetailsRepository;
    @KafkaListener(topics = "examFeePaid",groupId = "exam-fee-paid-group")
    public void consume(ExamFeePaidEvent event){
        ExamFeeDetails e=new ExamFeeDetails();
        e.setCourseCode(event.getCourseCode());
        e.setStudentId(event.getStudentId());
        e.setFeePaid(true);
        e.setSemester(event.getSemester());
        e.setProgramCode(event.getProgramCode());
        examFeeDetailsRepository.save(e);
    }
}
