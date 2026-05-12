package com.example.feemanagementservice.repository;

import com.example.feemanagementservice.entity.EnrolledCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnrolledCoursesRepository extends JpaRepository<EnrolledCourses,Long> {
    EnrolledCourses findByStudentIdAndSemester(UUID studentId, Integer semester);

    EnrolledCourses findByStudentIdAndSemesterAndProgram(UUID studentId, Integer semester, String program);
}
