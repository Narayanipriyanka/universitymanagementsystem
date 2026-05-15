package com.example.notificationcommunicationservice.service;

import com.example.events.*;
import com.example.facultyservice.entity.Payslip;
import com.example.notificationcommunicationservice.entity.Circulars;
import com.example.notificationcommunicationservice.entity.ParentDetails;
import com.example.notificationcommunicationservice.entity.StudentDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

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

    public void sendStudentCreatedMail(UUID studentID, String email, String username, String password) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi student, you are registered into  university by admin ");
        mailMessage.setText("welcome to our university,your student profile is being successfully registered into university database by admin kindly presevr the following credentials for further logins to university system\nYour student id:"+studentID+" \nyour username:"+username+"\n your password :"+password+"\n thanks & regards \n university team");
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
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filePath);
        }

        helper.addAttachment(file.getName(), file);
        mailSender.send(message);
    }

    public void sendAcademics(ParentDetails p, AcademicsEvent request) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(p.getEmail());
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi Parent,your child university Academics Record is here");
        mailMessage.setText("This is to inform you that ,your child has secured the following marks "+request.getMarks()+" with "+request.getGrade()+" grade in subjects"+request.getSubject()+" in the "+request.getSemester()+" semester \n thank you \n university team");
        mailSender.send(mailMessage);
    }

    public void sendBookReturnAlert(String email,BookReturnAlert request) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi Student,your book return date is"+request.getDaysLeft()+"days left");
        mailMessage.setText("This is to inform you that ,your "+request.getBookName()+" book return date is"+request.getReturnDate()+"which is very close only " +request.getDaysLeft()+" days left so kindly return the book before "+request.getReturnDate()+" to avoid Rs."+request.getFine()+ "fine \n thank you \n university team");
        mailSender.send(mailMessage);
    }

    public void sendBookCollectAlert(String email, BookCollectEvent request) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi Student,the book you are waiting is for is available for you");
        mailMessage.setText("This is to inform you that ,your "+request.getBookName()+" book reservation request is accepted now the book is available for you \n go to university page and borrow the book in the university page immediately otherwise the book maybe borrowed by some others \n thank you \n university team");
        mailSender.send(mailMessage);
    }

    public void sendResources(String email, DigitalResourcesEvent request) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setFrom(fromEmail);
        helper.setSubject("Hi,DigitalResources for the "+request.getCourseCode()+" is here");
        helper.setText("Here are the list of pdfs and videos for your "+request.getCourseCode()+" course. Download attached files.");
        List<String> filePaths=request.getFilePath();
        for(String filePath:filePaths){
        File file = new File(filePath);
        helper.addAttachment(file.getName(), file);}
        mailSender.send(message);
    }

    public void sendInvoice(String email, FeeInvoiceEvent request) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi Student,Payment success Invoice Generated");
        mailMessage.setText("This is to inform you that ,your Payment for"+request.getCategory()+" is success \nrecieved amount:"+request.getAmountPaid()+" remaining "+request.getCategory()+"  balance to be paid is "+request.getBalance()+" Total remaining balance to be apid for the current semester and program is"+request.getTotalBalance()+" \n thank you \n university team");
        mailSender.send(mailMessage);
    }

    public void sendPaymentLink(String email, PaymentLinkEvent request) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi Student,Here is the payment link");
        mailMessage.setText("This is to inform you that ,your Payment link is"+request.getPaymentlink()+" \nPay amount: Rs."+request.getAmount()+"  \n thank you \n university team");
        mailSender.send(mailMessage);
    }

    public void sendCircular(String email,Circulars circular) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi,Here is the new circular from university ");
        mailMessage.setText("This is to inform you that ,the university"+circular.getProgramCode()+" "+circular.getDeptCode()+" has a new circular released on "+circular.getReleaseDate()+" \n"+circular.getTitle()+" \n"+circular.getDescription()+"  \n thank you \n university team");
        mailSender.send(mailMessage);
    }

    public void sendOverDueAlert(String email, OverDueAlert request) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi,Fee Overdue Pay immediately!");
        mailMessage.setText("This is to inform you that ,Your fee balance is overdue by Rs."+request.getTotalBalance() +"The detailed fee balance deatials are given below:\n Tution fee Balance:Rs"+request.getTutionFeeBalance()+"\n Hostel Fee Balance:Rs"+request.getHostelFeeBalance()+"\nExam Fee balance:Rs"+request.getExamFeeBalance()+"\n Mess Fee Balance :Rs"+request.getMessFeeBalance()+"Records Fee Balance:Rs"+request.getRecordFeeBalance()+" kindly pay the fee and clear the balance as soon as possible to avoid the prohibitons and breakages in course exams  \n thank you \n university team");
        mailSender.send(mailMessage);
    }

    public void sendExamHallDetails(String email, ExamHallDetailsEvent request) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Hi student,check your Exam Hall and seatNo of Your upcoming Exam here !");
        mailMessage.setText("This is to inform you that ,Your Exam Hall No is"+request.getHallNo() +"\n seat No is:"+request.getSeatNo()+"\n Your Exam Hall is in"+request.getFloorNo()+"st Floor in University Examination Building \n All the best for your exam \n thank you \n university team");
        mailSender.send(mailMessage);
    }
}
