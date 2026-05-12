package com.example.feemanagementservice.service;

import com.example.events.EnrollEvent;
import com.example.events.PayFeesEvent;
import com.example.feemanagementservice.entity.*;
import com.example.feemanagementservice.repository.EnrolledCoursesRepository;
import com.example.feemanagementservice.repository.FeeRecordRepository;
import com.example.feemanagementservice.repository.FeeStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

public class CourseConsumer {
    @Autowired
    private FeeStructureRepository feeStructureRepository;
    @Autowired
    private EnrolledCoursesRepository enrolledCoursesRepository;
    @Autowired
    private FeeRecordRepository feeRecordRepository;

    @KafkaListener(topics="enrollCourse",groupId = "pay-tution-fees")
    public void consumeEnrollCourse(EnrollEvent dto){
        List<FeeStructure> f=feeStructureRepository.findAllByProgramCode(dto.getProgram());
        Double tutionFee=0.0;
        Double examFee=0.0;
        Double recordFee=0.0;
        for(FeeStructure fees:f){
            if(fees.getSemester()==dto.getSemester()){
                tutionFee+=fees.getTutionFee();
                examFee+=fees.getExamFee();
                recordFee+=fees.getRecordsFee();
            }
        }
        EnrolledCourses e=new EnrolledCourses();
        e.setCourseCode(dto.getCourseCode());
        e.setProgram(dto.getProgram());
        e.setStudentId(dto.getStudentId());
        e.setSemester(dto.getSemester());
        enrolledCoursesRepository.save(e);

        FeeRecord feeRecord=feeRecordRepository.findByStudentId(dto.getStudentId()).orElse(new FeeRecord());
        feeRecord.setExamFeeBalance(feeRecord.getExamFeeBalance()+examFee);
        feeRecord.setRecordFeeBalance(feeRecord.getRecordFeeBalance()+recordFee);
        feeRecord.setTutionFeeBalance(feeRecord.getTutionFeeBalance()+tutionFee);
        feeRecord.setTotalbalance(feeRecord.getTotalbalance()+examFee+recordFee+tutionFee);
        feeRecordRepository.save(feeRecord);

    }
}
