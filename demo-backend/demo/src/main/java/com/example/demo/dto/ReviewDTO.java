package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReviewDTO {
    private int id;
    private int rating;
    private String comment;
    private int serviceId;
    private String serviceName;
    private LocalDate date;
    public ReviewDTO(int id, int rating, String comment, int serviceId, String serviceName, LocalDate date) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.date = date;
    }
    public ReviewDTO() {
    }

    
}

