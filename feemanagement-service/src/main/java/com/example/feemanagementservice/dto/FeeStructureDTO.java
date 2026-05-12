package com.example.feemanagementservice.dto;

public class FeeStructureDTO {
    private String programCode;
    private String deptCode;
    private Integer semester;
    private Double tutionFee;
    private Double ExamFee;
    private Double recordsFee;

    public String getProgramCode() {
        return programCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public Double getTutionFee() {
        return tutionFee;
    }

    public Double getRecordsFee() {
        return recordsFee;
    }

    public void setRecordsFee(Double recordsFee) {
        this.recordsFee = recordsFee;
    }

    public Double getExamFee() {
        return ExamFee;
    }

    public void setExamFee(Double examFee) {
        ExamFee = examFee;
    }

    public void setTutionFee(Double tutionFee) {
        this.tutionFee = tutionFee;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }
}
