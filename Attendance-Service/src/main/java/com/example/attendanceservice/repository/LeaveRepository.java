package com.example.attendanceservice.repository;

import com.example.attendanceservice.entity.Leave;
import com.example.attendanceservice.entity.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {
    List<Leave> findAllByLeaveStatus(LeaveStatus leaveStatus);

    List<Leave> findAllByFacultyId(UUID facultyId);
}
