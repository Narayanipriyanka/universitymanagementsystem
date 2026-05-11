package com.example.gradeservice.dto;


import com.example.gradeservice.entity.AcademicsRecord;

import java.util.List;
import java.util.UUID;

public class TranscriptDTO {
    private UUID studentId;
    private String studentName;
    private List<AcademicsRecord> records;
    private Double cgpa;

    public TranscriptDTO(UUID studentId, List<AcademicsRecord> records, double cgpa) {
        this.studentId=studentId;
        this.studentName=firstName;
        this.records=records;
        this.cgpa=cgpa;}

    public UUID getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public List<AcademicsRecord> getRecords() {
        return records;
    }

    public void setRecords(List<AcademicsRecord> records) {
        this.records = records;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
