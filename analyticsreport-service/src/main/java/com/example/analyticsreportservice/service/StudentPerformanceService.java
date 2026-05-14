package com.example.analyticsreportservice.service;

import com.example.analyticsreportservice.dto.DepartmentWiseReport;
import com.example.analyticsreportservice.dto.StudentPerformanceDTO;
import com.example.analyticsreportservice.entity.AcademicsDetails;
import com.example.analyticsreportservice.entity.CourseCompletionDetails;
import com.example.analyticsreportservice.entity.CourseEnrollDetails;
import com.example.analyticsreportservice.repository.AcademicDetailsRepo;
import com.example.analyticsreportservice.repository.CourseEnrollDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentPerformanceService {
    @Autowired
    private AcademicDetailsRepo academicDetailsRepo;
    @Autowired
    private CourseEnrollDetailsRepo courseEnrollDetailsRepo;
    public StudentPerformanceDTO getStudentPerformanceBasedONCourse(String courseCode){
        List<AcademicsDetails> academicsDetails=academicDetailsRepo.findAllByCourse(courseCode);
        Integer totalStudents=0;
        Integer totalpassedStudents=0;
        Integer totalFailedStudents=0;
        Integer passPercentage=0;
        Integer failurePercentage=0;
        Integer totalAGrades=0;
        String overall=" ";
        for(AcademicsDetails a:academicsDetails){
           totalStudents++;
           if(a.getPass()){
               totalpassedStudents++;
           }
           else{
               totalFailedStudents++;
           }
           if(Objects.equals(a.getGrade(), "A+")){
             totalAGrades++;
           }

        }
        passPercentage=(totalpassedStudents/totalStudents)*100;
        failurePercentage= (totalFailedStudents/totalStudents)*100;
        if(passPercentage>failurePercentage&&passPercentage>60){
            overall+="good performance";
        } else if (passPercentage<60&&passPercentage>50){
            overall+="students not performed well";
        }
        else{
            overall+="students are not good at this course,need to improve";
        }
        return new StudentPerformanceDTO(totalpassedStudents,totalStudents,totalFailedStudents,totalAGrades,passPercentage,failurePercentage,overall);

    }
    public DepartmentWiseReport departmentWiseAnalytics(String deptCode){
        List<CourseEnrollDetails> courseEnrollDetails=courseEnrollDetailsRepo.findAllByDeptCode(deptCode);
        Integer nooFStudentsPassed=0;
        Integer nooFStudentFailed=0;
        Double passPercentage=0.0;
        Double failPercentage=0.0;
        Integer totalStudents=0;
        Integer noFStudentsTopped=0;

        for(CourseEnrollDetails c:courseEnrollDetails) {
            List<AcademicsDetails> academicsDetails = academicDetailsRepo.findAllByCourse(c.getCourseCode());
            for(AcademicsDetails a:academicsDetails){
                if(a.getPass()){
                    nooFStudentsPassed++;
                }
                else{
                    nooFStudentFailed++;
                }
                totalStudents++;
                if(a.getGrade()=="A+"){
                    noFStudentsTopped++;
                }
            }
        }
        passPercentage= (double) ((nooFStudentsPassed/totalStudents)*100);
        failPercentage= (double) ((nooFStudentFailed/totalStudents)*100);
        return new DepartmentWiseReport(totalStudents,nooFStudentsPassed,nooFStudentFailed,noFStudentsTopped,passPercentage,failPercentage);
    }
}
