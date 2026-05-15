package com.example.facultyservice.repository;

import com.example.facultyservice.entity.Course;
import com.example.facultyservice.entity.CourseAllocationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findAllByStatus(CourseAllocationStatus courseAllocationStatus);
}
