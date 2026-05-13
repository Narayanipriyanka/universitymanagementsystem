package com.example.examservice.repository;

import com.example.examservice.entity.ExamFeeDetails;
import com.example.examservice.entity.ExamRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRegisterRepository extends JpaRepository<ExamRegister,Long> {
    ExamRegister findByExamName(String exam);
}
