package com.example.enrollmentservice.controller;

import com.example.enrollmentservice.dto.EnrollDTO;
import com.example.enrollmentservice.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public String enrollInCOurse(EnrollDTO dto){
        return enrollmentService.enrollInCourse(dto);
    }
    @GetMapping("/available")
    public String seatAvailable(String courseCode){
        return enrollmentService.checkSeatAvailabilityInCourse(courseCode);
    }
    @PostMapping("/setSection")
    @PreAuthorize("hasRole('ADMIN')")
    public String setStudentToSection(String courseCode, UUID studentId){
        return enrollmentService.setStudentInSectionInCourse(courseCode,studentId);
    }
    @PutMapping("/drop")
    @PreAuthorize("hasRole('STUDENT')")
    public String dropCourse(String courseCode){
        return enrollmentService.dropCourse(courseCode);
    }
}
