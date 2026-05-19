package com.example.departmentservice.repository;

import com.example.departmentservice.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Long> {
    Optional<Program> findByNameAndProgramCode(String name, String programCode);
}
