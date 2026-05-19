package com.example.departmentservice.repository;

import com.example.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    List<Department> findAllByDeptIdIn(List<UUID> departmentIds);

    Department findByDepartmentCode(String depCode);

    List<Department> findAllByDepartmentCodeIn(List<String> departmentCodes);
}
