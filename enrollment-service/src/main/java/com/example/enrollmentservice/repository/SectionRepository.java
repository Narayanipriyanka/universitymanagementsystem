package com.example.enrollmentservice.repository;

import com.example.enrollmentservice.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {
    List<Section> findAllByCourseCode(String courseCode);
}
