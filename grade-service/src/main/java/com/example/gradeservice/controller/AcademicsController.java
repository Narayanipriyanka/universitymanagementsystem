package com.example.gradeservice.controller;



import com.example.gradeservice.dto.AcademicRecordDTO;
import com.example.gradeservice.dto.TranscriptDTO;
import com.example.gradeservice.entity.AcademicsRecord;
import com.example.gradeservice.service.AcademicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/grade/academic")
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
    @PostMapping(path = "/upload/csv", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "post results csv file", description = "only admins can uplaod all students results ")
    public List<AcademicsRecord> uploadCSV(@RequestParam("file") MultipartFile file) throws Exception {
        return academicService.addResultsCsv(file);

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
    @Operation(summary = "overall students pass and failure percentage of semester", description = "only admins will get this overall pass and failure percentages")
    @GetMapping("/passPercentage")
    @PreAuthorize("hasRole('ADMIN')")
    public String getPassPercentage(Integer seemster) {
        return academicService.passPercentage(seemster);
    }
    @Operation(summary = "result of a student in a semester", description = "only student will get this overall result in particular sem")
    @GetMapping("/result")
    @PreAuthorize("hasRole('STUDENT')")
    public String getResult(UUID studentiD,Integer semster) {
        return academicService.getResultInSemester(studentiD,semster);
    }



}

