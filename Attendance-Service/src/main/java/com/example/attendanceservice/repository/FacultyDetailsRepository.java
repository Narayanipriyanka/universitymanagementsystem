package com.example.attendanceservice.repository;

import com.example.attendanceservice.entity.FacultyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FacultyDetailsRepository extends JpaRepository<FacultyDetails, UUID> {
    FacultyDetails findByUsername(String username);
}
