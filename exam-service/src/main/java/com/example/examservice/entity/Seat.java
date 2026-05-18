package com.example.examservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.apache.kafka.common.protocol.types.Field;

import java.util.UUID;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatNo;
    private UUID studentId;
    private Boolean isAllocated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    @JsonIgnore
    private ExamHall examHall;

    public Long getSeatNo() {
        return seatNo;
    }

    public Boolean getAllocated() {
        return isAllocated;
    }

    public void setAllocated(Boolean allocated) {
        isAllocated = allocated;
    }

    public ExamHall getExamHall() {
        return examHall;
    }

    public void setExamHall(ExamHall examHall) {
        this.examHall = examHall;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public void setSeatNo(Long seatNo) {
        this.seatNo = seatNo;
    }
}
