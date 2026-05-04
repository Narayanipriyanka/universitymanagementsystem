package com.example.studentservice.controller;

import com.example.studentservice.dto.ScholarshipDTO;
import com.example.studentservice.dto.ScholarshipStatusDTO;
import com.example.studentservice.entity.Scholarship;
import com.example.studentservice.service.ScholarshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student/scholarships")
@Tag(name="student scholarship  controller",description="for student scholarship services like apply for scholarship,tracking scholarship status,")
public class ScholarshipController {
    @Autowired
    private ScholarshipService scholarshipService;

    @PostMapping("/apply")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Apply for a scholarship", description = "if you are a student apply for a scholarship here ")
    public ResponseEntity<String> create(@RequestBody ScholarshipDTO request) {
        return ResponseEntity.ok(scholarshipService.applyForScholarship(request));
    }
    @PutMapping("/status")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "update student's scholarship status", description = "here an admin can change the student sholarship sttus to invalid,payment processing,approved,under-review etc... ")
    public ResponseEntity<String> update(@RequestBody ScholarshipStatusDTO update) {
        return ResponseEntity.ok(scholarshipService.updateStatus(update));
    }
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "student can track their scholarship status and list of sholarships appied here")
    public ResponseEntity<List<Scholarship>> getStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(scholarshipService.getStudentScholarships(studentId));
    }
    @GetMapping("/status/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "student can track their scholarship status here")
    public Scholarship trackScholarship(@PathVariable Long id) {
        return scholarshipService.trackScholarship(id);
    }

}
