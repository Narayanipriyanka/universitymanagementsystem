package com.example.departmentservice.repository;

import com.example.departmentservice.entity.AllocationStatus;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.entity.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resources,Long> {
    List<Resources> findAllByStatus(AllocationStatus allocationStatus);

    Resources findByAllocatedDept(Department d);
}
