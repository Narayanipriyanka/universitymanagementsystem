package com.example.analyticsreportservice.repository;

import com.example.analyticsreportservice.entity.FeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeDetailsRepo extends JpaRepository<FeeDetails,Long> {
}
