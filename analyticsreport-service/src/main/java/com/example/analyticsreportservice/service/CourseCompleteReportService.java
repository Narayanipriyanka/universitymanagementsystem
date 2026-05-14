package com.example.analyticsreportservice.service;

import com.example.analyticsreportservice.dto.DepartmentWiseReport;
import com.example.analyticsreportservice.entity.AcademicsDetails;
import com.example.analyticsreportservice.entity.CourseEnrollDetails;
import com.example.analyticsreportservice.repository.AcademicDetailsRepo;
import com.example.analyticsreportservice.repository.CourseEnrollDetailsRepo;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseCompleteReportService {
    @Autowired
    private CourseEnrollDetailsRepo courseEnrollDetailsRepo;
    @Autowired
    private AcademicDetailsRepo academicDetailsRepo;

    public String courseCompletionRate(String courseCode,String programCode){
        List<CourseEnrollDetails> courseEnrollDetails=courseEnrollDetailsRepo.findAllByCourseCodeAndProgramCode(courseCode,programCode);
        List<AcademicsDetails> academicsDetails=academicDetailsRepo.findAllByCourseAndProgramCode(courseCode,programCode);
        Integer totalEnrolled=courseEnrollDetails.size();
        Integer totalCompleted=0;
        for(AcademicsDetails a:academicsDetails){
            if(a.getPass()){
                totalCompleted++;
            }
        }
        Double rateOfCompletion= (double) ((totalCompleted/totalEnrolled)*100);
        return "Total students enrolled in"+courseCode+"are :"+totalEnrolled+"\n total students completed the course successfully :"+totalCompleted+"\n rate of completion:"+rateOfCompletion;

    }


}
