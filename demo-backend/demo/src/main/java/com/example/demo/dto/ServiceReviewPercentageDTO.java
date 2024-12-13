package com.example.demo.dto;
import lombok.Data;

@Data
public class ServiceReviewPercentageDTO {
    private int serviceId;
    private String serviceName;
    private long totalReviews;
    private long reviewsInRange;
    private double percentage;
}
