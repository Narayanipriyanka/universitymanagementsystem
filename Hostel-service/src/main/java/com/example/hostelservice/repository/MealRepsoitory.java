package com.example.hostelservice.repository;

import com.example.hostelservice.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepsoitory extends JpaRepository<Meal,Long> {
    List<Meal> findAllByMessId(Long messId);
}
