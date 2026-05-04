package com.example.facultyservice.repository;

import com.example.facultyservice.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification,Long> {
}
