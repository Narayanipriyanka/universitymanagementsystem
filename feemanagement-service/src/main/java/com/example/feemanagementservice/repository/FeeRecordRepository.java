package com.example.feemanagementservice.repository;

import com.example.feemanagementservice.entity.FeeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeeRecordRepository extends JpaRepository<FeeRecord,Long> {
    Optional<FeeRecord> findByStudentId(UUID studentId);
}
