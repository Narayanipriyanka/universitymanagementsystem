package com.example.attendanceservice.controller;

import com.example.attendanceservice.entity.Attendance;
import com.example.attendanceservice.entity.AttendanceReport;
import com.example.attendanceservice.servcie.AttendanceService;
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
    public String addAttendance(){
        return attendanceService.addAttendance();
    }
    @PostMapping("/faculty")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Attendance> getAttendanceOfFaculty(@RequestParam UUID facultyId){
        return attendanceService.getAttendanceOfFaculty(facultyId);
    }
    @GetMapping("/absents")
    @PreAuthorize("hasRole('ADMIN')")
    public int getNoofAbsents(@RequestParam UUID facultyId,@RequestParam String month){
        return attendanceService.noOfAbsents(facultyId,month);
    }
    @GetMapping("/report")
    @PreAuthorize("hasRole('ADMIN')")
    public AttendanceReport getAttendanceReportPerMonth(@RequestParam UUID facultyId,@RequestParam String month){
        return attendanceService.getAttendanceReportOfMonth(facultyId,month);
    }
    @GetMapping("/belowPercentage")
    @PreAuthorize("hasRole('ADMIN')")
    public List<AttendanceReport> getAttednaceBypercentage(@RequestParam Integer percentage){
        return attendanceService.getAttendanceByPercentage(percentage);
    }
}
