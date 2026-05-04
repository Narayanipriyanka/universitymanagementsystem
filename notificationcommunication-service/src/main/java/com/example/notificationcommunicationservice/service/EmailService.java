package com.example.notificationcommunicationservice.service;

import com.example.events.LeaveEvent;
import com.example.events.LowAttendanceAlert;
import com.example.events.PayslipGeneratedEvent;
import com.example.facultyservice.entity.Payslip;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendIdCardEmail(String toEmail, String filePath) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setFrom(fromEmail);
        helper.setSubject("Hi,Your University ID Card Generated");
        helper.setText("Your ID card has been generated. Download attached file.");
        File file = new File(filePath);
        helper.addAttachment(file.getName(), file);
        mailSender.send(message);
    }
    public void sendScholarshipMail(String toEmail, String status){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi your scholarship is "+status);
        mailMessage.setText("your scholarship application is verified by the university admin and the curent status is "+status+"   for furhter queires contact unniversity management team or reapply");
        mailSender.send(mailMessage);}

    public void sendStudentCreatedMail(String email, String username, String password) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi student, you are registered into  university by admin ");
        mailMessage.setText("welcome to our university,your student profile is being successfully registered into university database by admin kindly presevr the following credentials for further logins to university system\nyour username:"+username+"\n your password :"+password+"\n thanks & regards \n university team");
        mailSender.send(mailMessage);
    }
    public void sendPaySlipMail(PayslipGeneratedEvent payslip){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(payslip.getEmail());
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi your payslip is generated for month "+payslip.getMonth());
        mailMessage.setText("your payslip for the "+payslip.getMonth()+" month is here\n with transcationId:"+payslip.getTransactionId()+"\n your earnings are:"+payslip.getEarnings()+"\n your deductions are: "+payslip.getDeductions()+"\n your net pay is :"+payslip.getNetPay()+"\n Thanks&regards\n university team");
        mailSender.send(mailMessage);}

    public void sendFacultyCreatedMail(String email, String username, String password) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi Faculty, you are registered into  university by admin ");
        mailMessage.setText("welcome to our university,your Faculty profile is being successfully registered into university database by admin kindly preserve the following credentials for further logins to university system\nyour username:"+username+"\n your password :"+password+"\n thanks & regards \n university team");
        mailSender.send(mailMessage);
    }

    public void sendLowAttendanceMail(LowAttendanceAlert request) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(request.getEmail());
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi Faculty, you are having less attendance ");
        mailMessage.setText("This is to inform you that ,As per the Attendance records,your Attendance percentage is "+request.getPercentage()+" which is marked as low percentage so kindly take it as a warn and improve the attendance !\n Thank you\n university team");
        mailSender.send(mailMessage);
    }
    public void sendLeaveStatuseMail(LeaveEvent request) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(request.getEmail());
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi Faculty, your leave status is here");
        mailMessage.setText("This is to inform you that ,your leave is"+request.getStatus()+" !\n Thank you\n university team");
        mailSender.send(mailMessage);
    }
    public void sendSyllabusEmail(String toEmail, String filePath,String code) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setFrom(fromEmail);
        helper.setSubject("Hi,Syllabus uplaoded for course"+code);
        helper.setText("Here is the syllabus for the "+code+" course you enrolled . Download attached file.");
        File file = new File(filePath);
        helper.addAttachment(file.getName(), file);
        mailSender.send(message);
    }
    public void sendMaterialsEmail(String toEmail, String filePath,String code) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setFrom(fromEmail);
        helper.setSubject("Hi,Materials for the course "+code+" is here");
        helper.setText("Here is the material for your "+code+" course. Download attached file.");
        File file = new File(filePath);
        helper.addAttachment(file.getName(), file);
        mailSender.send(message);
    }
}
