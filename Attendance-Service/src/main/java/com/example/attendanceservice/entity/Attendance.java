package com.example.attendanceservice.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID facultyId;
    private LocalDate date;
    @Enumerated
    private AttendanceStatus status;

    public Long getId() {
        return id;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UUID getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
