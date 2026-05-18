package com.example.hostelservice.repository;

import com.example.hostelservice.entity.MaintananceRequest;
import com.example.hostelservice.entity.MaintananceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintananceRequestRepository extends JpaRepository<MaintananceRequest,Long> {
    List<MaintananceRequest> findAllByStatus(MaintananceStatus maintananceStatus);

    List<MaintananceRequest> findAllByIssueType(String issueType);
}
