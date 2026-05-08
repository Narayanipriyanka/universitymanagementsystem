package com.example.libraryservice.repository;

import com.example.libraryservice.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByBookName(String bookName);

    Reservation findByPosition(int firstPosition);
}
