package com.example.gradeservice.repository;


import com.example.gradeservice.entity.AcademicsRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AcademicsRepository extends JpaRepository<AcademicsRecord,Long> {
    List<AcademicsRecord> findByStudentId(UUID studentId);

    List<AcademicsRecord> findAllBySemester(Integer semester);
}

