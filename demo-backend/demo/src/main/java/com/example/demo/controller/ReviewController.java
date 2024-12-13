package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.ServiceReviewPercentageDTO;
import com.example.demo.service.ReviewService;

@RestController
@RequestMapping("/api/v1/review")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<String> addReview(@RequestBody ReviewDTO reviewDTO) {
        String response = reviewService.addReview(reviewDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByService(@PathVariable int id) {
        List<ReviewDTO> reviews = reviewService.getReviewsByService(id);
        return ResponseEntity.ok(reviews);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<ReviewDTO>> getReviewsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ReviewDTO> reviews = reviewService.getReviewsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reviews);
    }

    /*@GetMapping("/compare-averages")
    public ResponseEntity<String> compareAverageRatings(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        double percentage = reviewService.compareAverageRatings(startDate, endDate);
        return ResponseEntity.ok("El promedio de calificaciones del rango es " + percentage + "% comparado con las otras fechas.");
    }*/
    @GetMapping("/review/percentages")
    public ResponseEntity<List<ServiceReviewPercentageDTO>> getReviewPercentages(
        @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
    List<ServiceReviewPercentageDTO> percentages = reviewService.calculateReviewPercentages(startDate, endDate);
    return ResponseEntity.ok(percentages);
    }


}
