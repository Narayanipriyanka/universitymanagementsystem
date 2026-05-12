package com.example.notificationcommunicationservice.repository;

import com.example.notificationcommunicationservice.entity.FacultyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FacultyDetailsRepository extends JpaRepository<FacultyDetails, UUID> {
}
