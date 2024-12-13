package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
    List<Review> findByServiceId(int serviceId);
    List<Review> findByDateBetween(LocalDate startDate, LocalDate endDate);
    //List<Review> findByDateNotBetween(LocalDate startDate, LocalDate endDate);
}

