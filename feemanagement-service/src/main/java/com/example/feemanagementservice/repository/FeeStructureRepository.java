package com.example.feemanagementservice.repository;

import com.example.feemanagementservice.entity.FeeStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeStructureRepository extends JpaRepository<FeeStructure,Long> {
    List<FeeStructure> findAllByProgramCode(String programCode);

    FeeStructure findByDeptCodeAndProgramCodeAndSemester(String deptCode, String programCode, Integer semester);
}
