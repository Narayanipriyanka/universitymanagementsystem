package com.example.attendanceservice.controller;

import com.example.attendanceservice.entity.Attendance;
import com.example.attendanceservice.entity.AttendanceReport;
import com.example.attendanceservice.servcie.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @PostMapping
    @PreAuthorize("hasRole('FACULTY')")
    @Operation(summary = "upload daily attendance of a faculty here",description = "only faculty can add his own attednance of that day here")
    public String addAttendance(){
        return attendanceService.addAttendance();
    }

    @PostMapping("/faculty")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get attendance of a faculty",description = "only admin can geta  faculties attendanve ")
    public List<Attendance> getAttendanceOfFaculty(@RequestParam UUID facultyId){
        return attendanceService.getAttendanceOfFaculty(facultyId);
    }
    @GetMapping("/absents")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get attendance of a faculty in a month",description = "only admin can geta  faculties monthly attendanve report")

    public int getNoofAbsents(@RequestParam UUID facultyId,@RequestParam String month){
        return attendanceService.noOfAbsents(facultyId,month);
    }
    @GetMapping("/report")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get attendance of a faculty in a month",description = "only admin can geta  faculties monthly attendanve report")
    public AttendanceReport getAttendanceReportPerMonth(@RequestParam UUID facultyId,@RequestParam String month){
        return attendanceService.getAttendanceReportOfMonth(facultyId,month);
    }
    @GetMapping("/belowPercentage")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get all faculties with this attendnace percentage",description = "only for admins to get faculties with this attednance percentage")
    public List<AttendanceReport> getAttednaceBypercentage(@RequestParam Integer percentage){
        return attendanceService.getAttendanceByPercentage(percentage);
    }
}
