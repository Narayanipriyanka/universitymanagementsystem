package com.example.analyticsreportservice.repository;

import com.example.analyticsreportservice.entity.AcademicsDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicDetailsRepo extends JpaRepository<AcademicsDetails,Long> {
    List<AcademicsDetails> findAllByCourse(String courseCode);

    List<AcademicsDetails> findAllByCourseAndProgramCode(String courseCode, String programCode);
}
