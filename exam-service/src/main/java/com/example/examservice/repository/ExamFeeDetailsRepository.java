package com.example.examservice.repository;

import com.example.examservice.entity.ExamFeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamFeeDetailsRepository extends JpaRepository<ExamFeeDetails,Long> {
}
