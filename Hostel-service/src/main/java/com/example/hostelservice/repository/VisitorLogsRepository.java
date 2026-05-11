package com.example.hostelservice.repository;

import com.example.hostelservice.entity.VisitorLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorLogsRepository extends JpaRepository<VisitorLogs,Long> {
}
