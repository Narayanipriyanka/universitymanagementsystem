package com.example.notificationcommunicationservice.service;

import com.example.events.*;
import com.example.notificationcommunicationservice.entity.ParentDetails;
import com.example.notificationcommunicationservice.entity.StudentDetails;
import com.example.notificationcommunicationservice.repository.ParentRepository;
import com.example.notificationcommunicationservice.repository.StudentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private final EmailService emailService;
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private StudentDetailsRepository studentDetailsRepository;
    public NotificationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }
    @KafkaListener(topics="sendIdcard",groupId="notification-idcard-group")
    public void consumeIdcard(IdCardEvent request) throws Exception {
        emailService.sendIdCardEmail(request.getEmail(),request.getFilePath());
    }
    @KafkaListener(topics="sendScholarship",groupId="notification-scholarship-group")
    public void consumeScholarship(SendScholarshipEvent request) throws Exception {
        emailService.sendScholarshipMail(request.getEmail(),request.getStatus());
    }
    @KafkaListener(topics="sendStudentCreated",groupId="notification-studentcreated-group")
    public void consumeStudentCreated(StudentCreatedEvent request) throws Exception {
       StudentDetails s=new StudentDetails();
       s.setEmail(request.getEmail());
       s.setUsername(request.getUsername());
       s.setId(request.getStudentId());
       studentDetailsRepository.save(s);
       emailService.sendStudentCreatedMail(request.getStudentId(),request.getEmail(),request.getUsername(),request.getPassword());
    }
    @KafkaListener(topics="sendPayslip",groupId="notification-payslip-group")
    public void consumeSendPayslip(PayslipGeneratedEvent request) throws Exception {
        emailService.sendPaySlipMail(request);
    }
    @KafkaListener(topics="sendFacultyCreated",groupId="notification-facultycreated-group")
    public void consumeFacultyCreated(FacultyCreatedEvent request) throws Exception {
        emailService.sendFacultyCreatedMail(request.getEmail(), request.getUsername(),request.getPassword());
    }
    @KafkaListener(topics = "lowAttendance",groupId = "notification-lowattendance-group")
        public void consumeLowAlert(LowAttendanceAlert request){
            emailService.sendLowAttendanceMail(request);
    }
    @KafkaListener(topics = "leave",groupId = "notification-leave-group")
    public void consumeLeave(LeaveEvent request){
        emailService.sendLeaveStatuseMail(request);
    }
    @KafkaListener(topics="sendSyllabusEmail",groupId = "notfication-syllabus-group")
    public void consumeSyllabus(SyllabusEvent request) throws Exception {
        emailService.sendSyllabusEmail(request.getEmail(), request.getFilePath(),request.getCourseCode());
    }
    @KafkaListener(topics="sendMaterialEmail",groupId = "notfication-syllabus-group")
    public void consumeMaterial(MaterialEvent request) throws Exception {
        emailService.sendMaterialsEmail(request.getEmail(), request.getFilePath(),request.getCourseCode());
    }
    @KafkaListener(topics="sendParent",groupId = "notfication-parent-group")
    public void consumeParent(ParentEvent request) throws Exception {
        ParentDetails p=new ParentDetails();
        p.setStudentId(request.getStudentId());
        p.setEmail(request.getEmail());
        p.setName(request.getName());
        p.setRelation(request.getRelation());
        p.setPhone(request.getPhone());
        parentRepository.save(p);
    }

    @KafkaListener(topics="sendAcademics",groupId="notification-academics-group")
    public void consumeAcademics(AcademicsEvent request) throws Exception {
        ParentDetails p=parentRepository.findByStudentId(request.getStudentId());
        emailService.sendAcademics(p,request);
    }
    @KafkaListener(topics="bookReturnAlert",groupId="notification-bookReturn-group")
    public void consumeBookReturnAlert(BookReturnAlert request) throws Exception {
       StudentDetails s=studentDetailsRepository.findById(request.getStudentId()).orElseThrow(()->new RuntimeException("no student found"));
       emailService.sendBookReturnAlert(s.getEmail(),request);
    }
    @KafkaListener(topics="bookIsGivenToYou",groupId="notification-bookCollect-group")
    public void consumeBookCollectAlert(BookCollectEvent request) throws Exception {
        StudentDetails s=studentDetailsRepository.findById(request.getStudentId()).orElseThrow(()->new RuntimeException("no student found"));
        emailService.sendBookCollectAlert(s.getEmail(),request);
    }
    @KafkaListener(topics="sendResources",groupId="notification-sendResources-group")
    public void consumeResources(DigitalResourcesEvent request) throws Exception {
        StudentDetails s=studentDetailsRepository.findByUsername(request.getStudentUsername());
        emailService.sendResources(s.getEmail(),request);
    }


}





