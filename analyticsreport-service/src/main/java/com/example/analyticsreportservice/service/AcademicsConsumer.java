package com.example.analyticsreportservice.service;

import com.example.analyticsreportservice.entity.AcademicsDetails;
import com.example.analyticsreportservice.entity.AttendanceDetails;
import com.example.analyticsreportservice.entity.CourseEnrollDetails;
import com.example.analyticsreportservice.entity.FeeDetails;
import com.example.analyticsreportservice.repository.AcademicDetailsRepo;
import com.example.analyticsreportservice.repository.AttendanceDetailsRepo;
import com.example.analyticsreportservice.repository.CourseEnrollDetailsRepo;
import com.example.analyticsreportservice.repository.FeeDetailsRepo;
import com.example.events.AcademicsEvent;
import com.example.events.AttendanceReportEvent;
import com.example.events.EnrollEvent;
import com.example.events.FeeInvoiceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AcademicsConsumer {
   @Autowired
   private CourseEnrollDetailsRepo courseEnrollDetailsRepo;
   @Autowired
   private AcademicDetailsRepo academicDetailsRepo;
   @Autowired
   private AttendanceDetailsRepo attendanceDetailsRepo;
   @Autowired
   private FeeDetailsRepo feeDetailsRepo;

    @KafkaListener(topics = "enrollCourse",groupId = "enroll-course-analytics-group")
    public void consumeEnrollDeatails(EnrollEvent event){
        CourseEnrollDetails c=courseEnrollDetailsRepo.findByCourseCode(event.getCourseCode()).orElse(new CourseEnrollDetails());
        c.setSemester(event.getSemester());
        c.setCourseId(event.getCourseId());
        c.setProgramCode(event.getProgram());
        c.setCourseCode(event.getCourseCode());
        List<UUID> students=c.getStudentIds();
        students.add(event.getStudentId());
        c.setStudentIds(students);
        courseEnrollDetailsRepo.save(c);
    }
    @KafkaListener(topics = "sendAcademics",groupId = "academics-analytics-group")
    public void consumeAcademics(AcademicsEvent event){
        AcademicsDetails academicsDetails=new AcademicsDetails();
        academicsDetails.setStudentId(event.getStudentId());
        academicsDetails.setGrade(event.getGrade());
        academicsDetails.setPass(event.getPass());
        academicsDetails.setMarks(event.getMarks());
        academicsDetails.setCourse(event.getSubject());
        academicsDetails.setProgramCode(event.getProgramCode());
        academicsDetails.setSemester(event.getSemester());
        academicDetailsRepo.save(academicsDetails);
    }
    @KafkaListener(topics = "sendAttendance",groupId = "attendnace-analytics-group")
    public void consumeAttendance(AttendanceReportEvent event){
        AttendanceDetails a=new AttendanceDetails();
        a.setFacultyId(event.getFacultyId());
        a.setAbsent(event.getAbsent());
        a.setAttendancePercentage(event.getAttendancePercentage());
        a.setPresent(event.getPresent());
        a.setMonth(event.getMonth());
        a.setTotalDays(event.getTotalDays());
        a.setLeave(event.getLeave());
        attendanceDetailsRepo.save(a);
    }
    @KafkaListener(topics = "sendInvoice",groupId = "fee-analytics-group")
    public void consumeFeesDetails(FeeInvoiceEvent event){
        FeeDetails feeDetails=new FeeDetails();
        feeDetails.setBalance(event.getBalance());
        feeDetails.setCategory(event.getCategory());
        feeDetails.setAmountPaid(event.getAmountPaid());
        feeDetails.setPaidDate(event.getPaidDate());
        feeDetails.setTotalBalance(event.getTotalBalance());
        feeDetails.setStudentId(event.getStudentId());
        feeDetailsRepo.save(feeDetails);
    }



}
