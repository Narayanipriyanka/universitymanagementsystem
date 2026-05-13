package com.example.examservice.repository;

import com.example.examservice.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
    Seat findByExamHall(String hallAllocated);

    List<Seat> findByExamHallAndIsAllocated(String hallAllocated, boolean b);
}
