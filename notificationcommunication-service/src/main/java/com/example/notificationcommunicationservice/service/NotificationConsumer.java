package com.example.notificationcommunicationservice.service;

import com.example.events.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private final EmailService emailService;
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
        emailService.sendStudentCreatedMail(request.getEmail(),request.getUsername(),request.getPassword());
    }
    @KafkaListener(topics="sendPayslip",groupId="notification-payslip-group")
    public void consumeSendPayslip(PayslipGeneratedEvent request) throws Exception {
        emailService.sendPaySlipMail(request);
    }
    @KafkaListener(topics="sendFacultyCreated",groupId="notification-facultycreated-group")
    public void consumeFacultyCreated(FacultyCreatedEvent request) throws Exception {
        emailService.sendFacultyCreatedMail(request.getEmail(), request.getUsername(),request.getPassword());
    }




}
