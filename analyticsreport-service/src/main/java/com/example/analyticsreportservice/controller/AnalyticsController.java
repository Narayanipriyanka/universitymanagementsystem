package com.example.analyticsreportservice.controller;

import com.example.analyticsreportservice.dto.AttendanceReportDetails;
import com.example.analyticsreportservice.dto.DepartmentWiseReport;
import com.example.analyticsreportservice.dto.FeeReportDTO;
import com.example.analyticsreportservice.dto.StudentPerformanceDTO;
import com.example.analyticsreportservice.entity.AttendanceDetails;
import com.example.analyticsreportservice.service.AttendanceAnalytics;
import com.example.analyticsreportservice.service.CourseCompleteReportService;
import com.example.analyticsreportservice.service.FeeAnalyticsService;
import com.example.analyticsreportservice.service.StudentPerformanceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {
    @Autowired
    private FeeAnalyticsService feeAnalyticsService;
    @Autowired
    private AttendanceAnalytics attendanceAnalytics;
    @Autowired
    private StudentPerformanceService studentPerformanceService;
    @Autowired
    private CourseCompleteReportService courseCompleteReportService;

    @GetMapping("/attendance/month")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get attendance analytics in a month",description = "only admin can get monthly attendanve report")
    public AttendanceReportDetails getAttendnceAnalyticsOFMonth(@RequestParam String month){
        return attendanceAnalytics.getAttendnaceAnalyticsByMonth(month);
    }
    @GetMapping("/attendance/percentage")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get attendance based on percnetage",description = "only admin can geta  faculties attendanve report below the given percentage")
    public List<AttendanceDetails> getAttendnceAnalyticsBypercentage(@RequestParam Integer percentage){
        return attendanceAnalytics.getAttendanceByPercentage(percentage);
    }
    @GetMapping("/attendance/faculty")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get attendance of a faculty ",description = "only admin can geta  faculties monthly attendanve report")
    public AttendanceDetails getAttendnceAnalyticsOFFaculty(@RequestParam UUID facultyId, @RequestParam String month){
        return attendanceAnalytics.getAttendanceReportOfMonth(facultyId,month);
    }
    @GetMapping("/course")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get course completion rate ",description = "only admin can geta course completion rate")
    public String getCourseCompletionRate(@RequestParam String courseCode, @RequestParam String programCode){
        return courseCompleteReportService.courseCompletionRate(courseCode,programCode);
    }
    @GetMapping("/fee")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get fee collection analytics rate in amonth",description = "only admin can get fee collected details of a month")
    public String getFeeCollected(@RequestParam String month){
        return feeAnalyticsService.feeCollectionAnalyticsInMonth(month);
    }
    @GetMapping("/feeReport")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get fee collection report in amonth",description = "only admin can get fee collectedreport of a month")
    public FeeReportDTO getFeeReport(@RequestParam String month){
        return feeAnalyticsService.feeCollectionReport(month);
    }
    @GetMapping("/studentperform")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get students performance in a course",description = "only admin can getstudent performance in a coiurse")
    public StudentPerformanceDTO getStudentPerformacee(@RequestParam String courseCode){
        return studentPerformanceService.getStudentPerformanceBasedONCourse(courseCode);
    }
    @GetMapping("/departmentwise")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get department wise students performance",description = "only admin can get department wise student performance ")
    public DepartmentWiseReport getDeptWiseStudentPerformacee(@RequestParam String deptCode){
        return studentPerformanceService.departmentWiseAnalytics(deptCode);
    }






}
