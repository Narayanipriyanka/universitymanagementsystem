package com.example.notificationcommunicationservice.repository;

import com.example.notificationcommunicationservice.entity.ParentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParentRepository extends JpaRepository<ParentDetails,Long> {
    ParentDetails findByStudentId(UUID studentId);

    ParentDetails findByStudentIdAndName(UUID studentId, String parentName);
}
