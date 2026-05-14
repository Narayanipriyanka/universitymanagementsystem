package com.example.gradeservice.dto;


import java.util.UUID;

public class AcademicRecordDTO {
    private UUID studentId;
    private Integer semester;
    private String subject;
    private String programCode;
    private Double midMarks;
    private Double FinalExamMarks;
    private Double quizMarks;
    private Double LabMarks;
    public UUID getStudentId() {
        return studentId;
    }

    public Integer getSemester() {
        return semester;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public Double getMidMarks() {
        return midMarks;
    }

    public Double getLabMarks() {
        return LabMarks;
    }

    public void setLabMarks(Double labMarks) {
        LabMarks = labMarks;
    }

    public Double getQuizMarks() {
        return quizMarks;
    }

    public void setQuizMarks(Double quizMarks) {
        this.quizMarks = quizMarks;
    }

    public Double getFinalExamMarks() {
        return FinalExamMarks;
    }

    public void setFinalExamMarks(Double finalExamMarks) {
        FinalExamMarks = finalExamMarks;
    }

    public void setMidMarks(Double midMarks) {
        this.midMarks = midMarks;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
