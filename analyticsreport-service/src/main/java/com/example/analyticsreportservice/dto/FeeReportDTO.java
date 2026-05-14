package com.example.analyticsreportservice.dto;

public class FeeReportDTO {
    private Double totalHostelFeeCollected;
    private Double totalExamFeeCollected;
    private Double totalLibraryFeeCollected;
    private Double totalTutionFeeCollected;
    private Double totalMessFeeCollected;
    private Double totalAmount;
    private Integer nOfStudentPaidHostel;
    private Integer nOfStudentPaidExam;
    private Integer nOfStudentPaidLibarary;
    private Integer nOfStudentPaidTution;
    private Integer nOfStudentPaidMess;

    public FeeReportDTO(Double totalHostelFeeCollected, Double totalExamFeeCollected, Double totalLibraryFeeCollected, Double totalTutionFeeCollected, Double totalMessFee, Integer studentExam, Integer studentHostel, Integer studentLibrary, Integer studentTution, Double totalCollection) {
        this.totalHostelFeeCollected = totalHostelFeeCollected;
        this.totalExamFeeCollected=totalExamFeeCollected;
        this.totalTutionFeeCollected=totalTutionFeeCollected;
        this.totalLibraryFeeCollected=totalLibraryFeeCollected;
        this.totalMessFeeCollected=totalMessFee;
        this.nOfStudentPaidExam=studentExam;
        this.nOfStudentPaidHostel=studentHostel;
        this.nOfStudentPaidLibarary=studentLibrary;
        this.nOfStudentPaidTution=studentTution;
        this.totalAmount=totalCollection;
    }

    public Double getTotalHostelFeeCollected() {
        return totalHostelFeeCollected;
    }

    public Integer getnOfStudentPaidMess() {
        return nOfStudentPaidMess;
    }

    public void setnOfStudentPaidMess(Integer nOfStudentPaidMess) {
        this.nOfStudentPaidMess = nOfStudentPaidMess;
    }

    public Integer getnOfStudentPaidTution() {
        return nOfStudentPaidTution;
    }

    public void setnOfStudentPaidTution(Integer nOfStudentPaidTution) {
        this.nOfStudentPaidTution = nOfStudentPaidTution;
    }

    public Integer getnOfStudentPaidLibarary() {
        return nOfStudentPaidLibarary;
    }

    public void setnOfStudentPaidLibarary(Integer nOfStudentPaidLibarary) {
        this.nOfStudentPaidLibarary = nOfStudentPaidLibarary;
    }

    public Integer getnOfStudentPaidExam() {
        return nOfStudentPaidExam;
    }

    public void setnOfStudentPaidExam(Integer nOfStudentPaidExam) {
        this.nOfStudentPaidExam = nOfStudentPaidExam;
    }

    public Integer getnOfStudentPaidHostel() {
        return nOfStudentPaidHostel;
    }

    public void setnOfStudentPaidHostel(Integer nOfStudentPaidHostel) {
        this.nOfStudentPaidHostel = nOfStudentPaidHostel;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalMessFeeCollected() {
        return totalMessFeeCollected;
    }

    public void setTotalMessFeeCollected(Double totalMessFeeCollected) {
        this.totalMessFeeCollected = totalMessFeeCollected;
    }

    public Double getTotalTutionFeeCollected() {
        return totalTutionFeeCollected;
    }

    public void setTotalTutionFeeCollected(Double totalTutionFeeCollected) {
        this.totalTutionFeeCollected = totalTutionFeeCollected;
    }

    public Double getTotalLibraryFeeCollected() {
        return totalLibraryFeeCollected;
    }

    public void setTotalLibraryFeeCollected(Double totalLibraryFeeCollected) {
        this.totalLibraryFeeCollected = totalLibraryFeeCollected;
    }

    public Double getTotalExamFeeCollected() {
        return totalExamFeeCollected;
    }

    public void setTotalExamFeeCollected(Double totalExamFeeCollected) {
        this.totalExamFeeCollected = totalExamFeeCollected;
    }

    public void setTotalHostelFeeCollected(Double totalHostelFeeCollected) {
        this.totalHostelFeeCollected = totalHostelFeeCollected;
    }
}
