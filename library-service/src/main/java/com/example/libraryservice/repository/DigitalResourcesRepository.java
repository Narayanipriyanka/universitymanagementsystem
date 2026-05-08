package com.example.libraryservice.repository;

import com.example.libraryservice.entity.DigitalResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigitalResourcesRepository extends JpaRepository<DigitalResource,Long> {
    DigitalResource findByCourseCode(String courseCode);

    List<DigitalResource> findAllByCourseCode(String courseCode);
}
