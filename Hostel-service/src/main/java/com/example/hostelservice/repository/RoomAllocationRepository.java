package com.example.hostelservice.repository;

import com.example.hostelservice.entity.RoomAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomAllocationRepository extends JpaRepository<RoomAllocation,Long> {

}
