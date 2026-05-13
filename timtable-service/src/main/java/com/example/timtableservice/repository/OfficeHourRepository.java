package com.example.timtableservice.repository;

import com.example.timtableservice.entity.OfficeHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OfficeHourRepository extends JpaRepository<OfficeHour,Long> {
    OfficeHour findByFacultyId(UUID facultyId);
}
