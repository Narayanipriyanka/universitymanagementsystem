package com.example.examservice.repository;

import com.example.examservice.entity.RevaluationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevaluationRequestRepository extends JpaRepository<RevaluationRequest,Long> {
    RevaluationRequest findByExamName(String examName);
}
