package com.example.notificationcommunicationservice.service;

import com.example.notificationcommunicationservice.entity.Circulars;
import com.example.notificationcommunicationservice.entity.FacultyDetails;
import com.example.notificationcommunicationservice.entity.StudentDetails;
import com.example.notificationcommunicationservice.repository.FacultyDetailsRepository;
import com.example.notificationcommunicationservice.repository.StudentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private FacultyDetailsRepository facultyDetailsRepository;
    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

     public void sendCircular(Circulars circulars) {
         List<StudentDetails> s=studentDetailsRepository.findAll();
         List<FacultyDetails> f=facultyDetailsRepository.findAll();
         for(StudentDetails studentDetails:s){
             emailService.sendCircular(studentDetails.getEmail(),circulars);
         }
         for(FacultyDetails facultyDetails:f){
             emailService.sendCircular(facultyDetails.getEmail(), circulars);
         }
     }

}
