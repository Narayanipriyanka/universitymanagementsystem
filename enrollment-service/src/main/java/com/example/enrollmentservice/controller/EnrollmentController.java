package com.example.enrollmentservice.controller;

import com.example.enrollmentservice.dto.EnrollDTO;
import com.example.enrollmentservice.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/enrollments")
@Tag(name = "university course Enrollment controller")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/available")
    @Operation(summary = "any one  can get reamining seats of a course here")
    public String seatAvailable(String courseCode){
        return enrollmentService.checkSeatAvailabilityInCourse(courseCode);
    }
    @PostMapping("/setSection")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can set student to a section among all sections ina course here")
    public String setStudentToSection(String courseCode, UUID studentId){
        return enrollmentService.setStudentInSectionInCourse(courseCode,studentId);
    }
    @PutMapping("/drop")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "only student cn drop from a course here")
    public String dropCourse(String courseCode){
        return enrollmentService.dropCourse(courseCode);
    }
}
