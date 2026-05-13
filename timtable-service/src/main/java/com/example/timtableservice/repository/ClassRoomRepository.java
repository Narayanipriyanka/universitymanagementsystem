package com.example.timtableservice.repository;

import com.example.timtableservice.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom,Long> {
    ClassRoom findByRoomNo(String roomNo);

    List<ClassRoom> findAllByProgram(String programCode);

    List<ClassRoom> findAllByProgramAndDept(String programCode, String deptCode);
}
