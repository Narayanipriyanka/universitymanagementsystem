package com.example.studentservice.controller;

import com.example.studentservice.dto.AcademicRecordDTO;
import com.example.studentservice.dto.TranscriptDTO;
import com.example.studentservice.entity.AcademicsRecord;
import com.example.studentservice.service.AcademicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student/academic")
@Tag(name="student academics  controller",description="for student academic deatils like faculty will uplaod marks and students can get transcripts")
public class AcademicsController {
    @Autowired
    private AcademicService academicService;
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    @Operation(summary = "add marks for a subject scored by student", description = "the respective facutly will upload marks scord by a student in his subject marks ")
    public AcademicsRecord add(@RequestBody AcademicRecordDTO dto) {
        return academicService.addRecord(dto);
    }
    @GetMapping("/history/{studentId}")
    @Operation(summary = "get student's academics history ", description = "for students academics history click here")
    public List<AcademicsRecord> history(@PathVariable UUID studentId) {
        return academicService.getAllAcademicRecordsOfStudent(studentId);
    }

    @Operation(summary = "Student Academics and Marks report", description = "for student's all academic records and reports click here ")
    @GetMapping("/transcript/{studentId}")
    public TranscriptDTO transcript(@PathVariable UUID studentId) {
        return academicService.getTranscriptReport(studentId);
    }
}
