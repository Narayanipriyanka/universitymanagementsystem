package com.example.examservice.dto;

public class ReevaluationDTO {
    private String examName;
    private Long examId;
    private Double fee;
    private Integer semester;
    private String programCode;
    private String deptCode;

    public String getExamName() {
        return examName;
    }

    public Long getExamId() {
        return examId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
}
