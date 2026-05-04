package com.example.attendanceservice.repository;

import com.example.attendanceservice.entity.AttendanceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttendanceReportRepository extends JpaRepository<AttendanceReport,Long> {
    List<AttendanceReport> findAllByFacultyId(UUID facultyId);

    List<AttendanceReport> findAllByAttendancePercentage(Integer percentage);
}
