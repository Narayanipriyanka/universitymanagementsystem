package com.example.facultyservice.repository;

import com.example.facultyservice.entity.OfficeHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfficeHoursRepository extends JpaRepository<OfficeHours,Long> {
    OfficeHours findByFacultyId(UUID facultyId);
}
