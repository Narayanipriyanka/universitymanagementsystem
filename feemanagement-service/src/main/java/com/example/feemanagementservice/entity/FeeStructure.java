package com.example.feemanagementservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FeeStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String programCode;
    private String deptCode;
    private Integer semester;
    private Double tutionFee;
    private Double ExamFee;
    private Double recordsFee;

    public Long getId() {
        return id;
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

    public Double getTutionFee() {
        return tutionFee;
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

    public void setId(Long id) {
        this.id = id;
    }
}
