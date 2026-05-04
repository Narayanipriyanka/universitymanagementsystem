package com.example.departmentservice.repository;

import com.example.departmentservice.entity.Budget;
import com.example.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {

    Budget findByDepartment(Department d);

}
