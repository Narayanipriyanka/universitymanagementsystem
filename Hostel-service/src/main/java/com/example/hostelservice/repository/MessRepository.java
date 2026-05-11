package com.example.hostelservice.repository;

import com.example.hostelservice.entity.Mess;
import com.example.hostelservice.entity.MessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessRepository extends JpaRepository<Mess,Long> {
    List<Mess> findByType(MessType type);

    List<Mess> findAllByType(MessType messType);
}
