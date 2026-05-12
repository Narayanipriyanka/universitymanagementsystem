package com.example.feemanagementservice.repository;

import com.example.feemanagementservice.entity.EnrolledCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolledCoursesRepository extends JpaRepository<EnrolledCourses,Long> {
}
