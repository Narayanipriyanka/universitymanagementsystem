package com.example.libraryservice.dto;

import com.example.libraryservice.entity.BookStatus;

import java.util.UUID;

public class BookDTO {
    private String bookName;
    private String author;
    private Integer copies;
    private UUID studentId;
    private String courseCode;
    private String deptCode;
    private Double mrp;
    public String getBookName() {
        return bookName;
    }

    public Double getMrp() {
        return mrp;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
