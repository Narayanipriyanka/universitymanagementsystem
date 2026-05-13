package com.example.timtableservice.repository;

import com.example.timtableservice.entity.FacultyClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface FacultyClassRoomRepository extends JpaRepository<FacultyClassRoom,Long> {
    FacultyClassRoom findByRoomNoAndStartTime(String roomNo, LocalTime nextPeriod);

    Boolean existsByRoomNoAndStartTime(String roomNo, LocalTime nextPeriod);

    List<FacultyClassRoom> findAllByRoomNo(String roomNo);
}
