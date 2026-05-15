package com.example.studentservice.controller;

import com.example.studentservice.dto.StudentRegisterDTO;
import com.example.studentservice.entity.Student;
import com.example.studentservice.service.IDCardGenerationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student/idcard")
@Tag(name="University Idcard services Controller ")
public class IDCardController {
    @Autowired
    private IDCardGenerationService service;
    @PostMapping(path = "/apply", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "apply for idcard ", description = "after registration student should apply for id card with photo ready for applying  ")
    public ResponseEntity<String> applyForIDcard(@RequestParam UUID studentID,@RequestParam String fullname,@RequestParam String fatherName, @RequestParam String email, @RequestParam String gender, @RequestParam String address, @RequestParam String bloodGroup, @RequestParam String phone, @RequestParam MultipartFile photo) throws IOException {
        String response = service.applyForIDCard(studentID,fullname,fatherName,email,gender,address,bloodGroup,phone,photo);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/generate/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "generate id card for student ", description = "only for admins , for genertaing id cards for newly registered and applied students")
    public ResponseEntity<String> generateIdCard(@PathVariable UUID studentId) throws Exception {
        String result = service.generateIdCard(studentId);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/pending")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "for pending students list", description = " only for admins ,the students whose profile is pending is shown her")
    public ResponseEntity<List<Student>> getPendingStudents() {
        return service.getPendingStudents();
    }
}
