package com.example.analyticsreportservice.repository;

import com.example.analyticsreportservice.entity.CourseEnrollDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseEnrollDetailsRepo extends JpaRepository<CourseEnrollDetails,Long> {

    Optional<CourseEnrollDetails> findByCourseCode(String courseCode);

    List<CourseEnrollDetails> findAllByCourseCode(String courseCode);

    List<CourseEnrollDetails> findAllByCourseCodeAndProgramCode(String courseCode, String programCode);

    List<CourseEnrollDetails> findAllByDeptCode(String deptCode);
}
