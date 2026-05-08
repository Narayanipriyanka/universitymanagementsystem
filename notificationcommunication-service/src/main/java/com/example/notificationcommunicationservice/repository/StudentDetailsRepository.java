package com.example.notificationcommunicationservice.repository;

import com.example.notificationcommunicationservice.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, UUID> {
}
