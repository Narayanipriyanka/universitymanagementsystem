package com.example.events;

import java.util.UUID;

public class BookCollectEvent {
    private UUID studentId;
    private Long bookId;
    private String bookName;
    public BookCollectEvent(UUID studentId, Long bookId, String bookName) {
        this.studentId=studentId;
        this.bookId=bookId;
        this.bookName=bookName;
    }
    public BookCollectEvent(){

    }

    public UUID getStudentId() {
        return studentId;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
