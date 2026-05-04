package com.example.facultyservice.repository;

import com.example.facultyservice.entity.Course;
import com.example.facultyservice.entity.CourseAllocationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findAllByStatus(CourseAllocationStatus courseAllocationStatus);
}
