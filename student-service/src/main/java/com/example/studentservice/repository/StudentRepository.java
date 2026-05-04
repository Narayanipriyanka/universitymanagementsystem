package com.example.studentservice.repository;

import com.example.studentservice.entity.Student;
import com.example.studentservice.entity.StudentIDcardStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByUserId(String userId);

    boolean existsByEmail(String email);

    List<Student> findByStatus(StudentIDcardStatus studentIDcardStatus);

    Student findByEmail(String email);

    List<Student> findAllByIdIn(List<UUID> studentId);

    Student findByUsername(String userName);
}
