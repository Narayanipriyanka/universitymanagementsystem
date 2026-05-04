package com.example.courseservice.repository;

import com.example.courseservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findBySem(Integer sem);

    Course findByCode(String courseCode);
}
