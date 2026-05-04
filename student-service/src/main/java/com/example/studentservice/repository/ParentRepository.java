package com.example.studentservice.repository;

import com.example.studentservice.entity.Parent;
import com.example.studentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
}
