package com.example.facultyservice.repository;

import com.example.facultyservice.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, UUID> {
    Faculty findByEmail(String email);
}
