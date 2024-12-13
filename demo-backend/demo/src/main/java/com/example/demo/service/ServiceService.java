package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ServiceDTO;

public interface ServiceService {
    String addService(ServiceDTO serviceDTO);
    List<ServiceDTO> getAllServices();
    ServiceDTO getServiceById(int serviceId);
    String updateService(ServiceDTO serviceDTO);
    String deleteService(int serviceId);
    List<ServiceDTO> compareServices();
}
