package com.example.enrollmentservice.repository;

import com.example.enrollmentservice.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollnmentRepository extends JpaRepository<Enrollment,Long> {
    List<Enrollment> findAllByCourseCode(String courseCode);
}
