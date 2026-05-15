package com.example.studentservice.repository;

import com.example.studentservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByStudentsId(UUID studentId);

    Course findByCourseCode(String courseCode);
}
