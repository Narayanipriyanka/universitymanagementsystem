package com.example.analyticsreportservice.repository;

import com.example.analyticsreportservice.entity.AttendanceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttendanceDetailsRepo extends JpaRepository<AttendanceDetails,Long> {
    List<AttendanceDetails> findAllByAttendancePercentage(Integer percentage);

    List<AttendanceDetails> findAllByFacultyId(UUID facultyId);

    List<AttendanceDetails> findAllByMonth(String month);
}
