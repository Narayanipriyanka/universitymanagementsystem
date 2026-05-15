package com.example.events;

import java.time.LocalDate;
import java.util.UUID;

public class BookReturnAlert {
    private UUID studentId;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private String bookName;
    private Double fine;
    private Long daysLeft;
    public BookReturnAlert(){

    }
    public BookReturnAlert(UUID studentId, LocalDate returnDate, LocalDate issueDate, Double fine, String bookName, long daysLeft) {
    this.studentId=studentId;
    this.fine=fine;
    this.bookName=bookName;
    this.issueDate=issueDate;
    this.returnDate=returnDate;
    this.daysLeft=daysLeft;}

    public Long getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(Long daysLeft) {
        this.daysLeft = daysLeft;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
