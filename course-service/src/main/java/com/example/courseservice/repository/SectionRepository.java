package com.example.courseservice.repository;

import com.example.courseservice.entity.Sections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Sections,Long> {
    Sections finAllByCourseCode(String code);

    List<Sections> findAllByCourseCode(java.lang.String code);
}
