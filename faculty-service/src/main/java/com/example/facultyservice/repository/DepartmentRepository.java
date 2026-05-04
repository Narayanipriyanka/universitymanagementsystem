package com.example.facultyservice.repository;

import com.example.facultyservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {

    List<Department> findByDepartmentNameIn(List<String> deptName);
}
