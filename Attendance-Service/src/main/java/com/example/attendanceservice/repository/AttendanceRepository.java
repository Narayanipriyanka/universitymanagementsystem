package com.example.attendanceservice.repository;

import com.example.attendanceservice.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    List<Attendance> findAllByFacultyId(UUID facultyId);

    Attendance findByDate(LocalDate now);
}
