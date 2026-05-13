package com.example.timtableservice.repository;

import com.example.timtableservice.entity.FacultyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FacultyCourseRepository extends JpaRepository<FacultyCourse,Long> {
    FacultyCourse findByFacultyId(UUID facultyId);
}
