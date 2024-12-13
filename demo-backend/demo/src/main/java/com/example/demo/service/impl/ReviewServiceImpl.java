package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.ServiceReviewPercentageDTO;
import com.example.demo.model.Review;
import com.example.demo.model.Services;
import com.example.demo.repository.ReviewRepo;
import com.example.demo.repository.ServiceRepo;
import com.example.demo.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepo reviewRepository;

    @Autowired
    private ServiceRepo serviceRepository;

    @Override
    public String addReview(ReviewDTO reviewDTO) {
        // Fetch the related service
        Services service = serviceRepository.findById(reviewDTO.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        // Create and save the Review entity
        Review review = new Review();
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setService(service);

        reviewRepository.save(review);

        return "Review added successfully";
    }

    @Override
    public List<ReviewDTO> getReviewsByService(int serviceId) {
        // Fetch reviews for the given service
        List<Review> reviews = reviewRepository.findByServiceId(serviceId);

        // Convert entities to DTOs
        return reviews.stream().map(review -> new ReviewDTO(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getService().getId(),
                review.getService().getServiceName(),
                review.getDate()
        )).collect(Collectors.toList());
    }

    @Override
    public double getAverageRatingByService(int serviceId) {
        // Calculate the average rating for the given service
        List<Review> reviews = reviewRepository.findByServiceId(serviceId);
        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
    @Override
    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();

        return reviews.stream().map(review -> new ReviewDTO(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getService().getId(),
                review.getService().getServiceName(),
                review.getDate()
        )).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Review> reviews = reviewRepository.findByDateBetween(startDate, endDate);
        return reviews.stream().map(review -> {
            ReviewDTO dto = new ReviewDTO();
            dto.setId(review.getId());
            dto.setRating(review.getRating());
            dto.setComment(review.getComment());
            dto.setServiceId(review.getService().getId());
            dto.setServiceName(review.getService().getServiceName());
            dto.setDate(review.getDate());
            return dto;
        }).collect(Collectors.toList());
    }

    

    @Override
    public List<ServiceReviewPercentageDTO> calculateReviewPercentages(LocalDate startDate, LocalDate endDate) {
    List<Review> allReviews = reviewRepository.findAll();

    Map<Integer, List<Review>> reviewsGroupedByService = allReviews.stream()
            .collect(Collectors.groupingBy(review -> review.getService().getId()));

    List<ServiceReviewPercentageDTO> percentages = new ArrayList<>();
    for (Map.Entry<Integer, List<Review>> entry : reviewsGroupedByService.entrySet()) {
        int serviceId = entry.getKey();
        List<Review> serviceReviews = entry.getValue();

        long totalReviews = serviceReviews.size();
        long reviewsInRange = serviceReviews.stream()
                .filter(review -> !review.getDate().isBefore(startDate) && !review.getDate().isAfter(endDate))
                .count();

        double percentage = totalReviews == 0 ? 0.0 : (reviewsInRange * 100.0) / totalReviews;

        ServiceReviewPercentageDTO dto = new ServiceReviewPercentageDTO();
        dto.setServiceId(serviceId);
        dto.setServiceName(serviceReviews.get(0).getService().getServiceName());
        dto.setTotalReviews(totalReviews);
        dto.setReviewsInRange(reviewsInRange);
        dto.setPercentage(percentage);

        percentages.add(dto);
    }

    return percentages;
    }


}
