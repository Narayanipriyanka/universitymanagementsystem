package com.example.hostelservice.repository;

import com.example.hostelservice.entity.Hostel;
import com.example.hostelservice.entity.HostelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostelRepository extends JpaRepository<Hostel,Long> {
    List<Hostel> findByType(HostelType type);
}
