package com.example.demo.dto;

import lombok.Data;

@Data
public class ServiceDTO {
    private int id;
    private String serviceName;
    private String description;
    private double price;
    private int employeeId;
    private Double puntajeFinal;
    private Double averageRating;
    public ServiceDTO(int id, String serviceName, String description, double price, int employeeId, Double puntajeFinal,
            Double averageRating) {
        this.id = id;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.employeeId = employeeId;
        this.puntajeFinal = puntajeFinal;
        this.averageRating = averageRating;
    }
    public ServiceDTO() {
    }
}
