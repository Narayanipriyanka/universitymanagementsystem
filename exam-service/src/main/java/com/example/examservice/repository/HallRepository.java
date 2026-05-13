package com.example.examservice.repository;

import com.example.examservice.entity.ExamHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<ExamHall,Long> {
    ExamHall findByHallNo(String hallNo);

    List<ExamHall> findAllByIsAvailable(boolean isAvailable);
}
