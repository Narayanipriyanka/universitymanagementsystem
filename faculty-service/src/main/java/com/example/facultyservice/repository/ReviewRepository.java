package com.example.facultyservice.repository;

import com.example.facultyservice.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Reviews,Long> {
    List<Reviews> findAllByFacultyId(UUID facultyId);
}
