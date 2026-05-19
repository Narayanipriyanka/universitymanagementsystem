package com.example.notificationcommunicationservice.repository;

import com.example.notificationcommunicationservice.entity.ParentCommunication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentCommunicationRepo extends JpaRepository<ParentCommunication,Long> {
}
