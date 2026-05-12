package com.example.feemanagementservice.repository;

import com.example.feemanagementservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Payment findByStudentId(UUID studentId);

    List<Payment> findAllByStudentId(UUID studentId);
}
