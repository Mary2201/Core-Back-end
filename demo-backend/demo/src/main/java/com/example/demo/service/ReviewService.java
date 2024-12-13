package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.ServiceReviewPercentageDTO;

public interface ReviewService {
    String addReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getReviewsByService(int serviceId);

    double getAverageRatingByService(int serviceId);
    List<ReviewDTO> getAllReviews();
    List<ReviewDTO> getReviewsByDateRange(LocalDate startDate, LocalDate endDate);
    List<ServiceReviewPercentageDTO> calculateReviewPercentages(LocalDate startDate, LocalDate endDate);
}
