package com.example.notificationcommunicationservice.service;

import com.example.notificationcommunicationservice.dto.ParentCommunicationDTO;
import com.example.notificationcommunicationservice.entity.*;
import com.example.notificationcommunicationservice.repository.FacultyDetailsRepository;
import com.example.notificationcommunicationservice.repository.ParentCommunicationRepo;
import com.example.notificationcommunicationservice.repository.ParentRepository;
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
    @Autowired
    private ParentCommunicationRepo parentCommunicationRepo;
    @Autowired
    private ParentRepository parentRepository;

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
     public String parentCommunication(ParentCommunicationDTO dto){
         ParentCommunication communication=new ParentCommunication();
         ParentDetails s=parentRepository.findByStudentIdAndName(dto.getStudentId(),dto.getParentName());
         StudentDetails studentDetails=studentDetailsRepository.findById(dto.getStudentId()).orElseThrow(()->new RuntimeException("no student found with this id"));
         communication.setDescription(dto.getDescription());
         communication.setIssue(dto.getIssue());
         communication.setParentName(dto.getParentName());
         communication.setResolved(false);
         communication.setStudentId(dto.getStudentId());
         communication.setStudentName(studentDetails.getUsername());
         parentCommunicationRepo.save(communication);
         emailService.sendParentCommunication(s.getEmail(),dto);
         return "mail sent to parent regarding the issue";
     }
}
