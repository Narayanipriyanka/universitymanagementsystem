package com.example.studentservice.repository;

import com.example.studentservice.entity.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScholarshipRepository extends JpaRepository<Scholarship,Long> {

    List<Scholarship> findByStudentId(UUID studentId);

    Scholarship findByScholarshipName(String scholarshipName);
}
