package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ServiceDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.Services;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.repository.ServiceRepo;
import com.example.demo.service.ServiceService;

@Service
public class ServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public String addService(ServiceDTO serviceDTO) {
        Employee employee = employeeRepo.findById(serviceDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Services service = new Services();
        service.setServiceName(serviceDTO.getServiceName());
        service.setDescription(serviceDTO.getDescription());
        service.setPrice(serviceDTO.getPrice());
        service.setEmployee(employee);

        serviceRepo.save(service);
        return "Service added successfully";
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        List<Services> services = serviceRepo.findAll();
        return services.stream().map(service -> {
            ServiceDTO dto = new ServiceDTO();
            dto.setId(service.getId());
            dto.setServiceName(service.getServiceName());
            dto.setDescription(service.getDescription());
            dto.setPrice(service.getPrice());
            dto.setEmployeeId(service.getEmployee().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO> compareServices() {
        // Compare services based on ratings or other criteria.
        // Add sorting/filtering logic here.
        return getAllServices();
    }

    @Override
    public ServiceDTO getServiceById(int serviceId) {
        Services service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        ServiceDTO dto = new ServiceDTO();
        dto.setId(service.getId());
        dto.setServiceName(service.getServiceName());
        dto.setDescription(service.getDescription());
        dto.setPrice(service.getPrice());
        dto.setEmployeeId(service.getEmployee().getId());
        return dto;
    }


    @Override
    public String updateService(ServiceDTO serviceDTO) {
        Services service = serviceRepo.findById(serviceDTO.getId())
                .orElseThrow(() -> new RuntimeException("Service not found"));
    
        service.setServiceName(serviceDTO.getServiceName());
        service.setDescription(serviceDTO.getDescription());
        service.setPrice(serviceDTO.getPrice());
        serviceRepo.save(service);
    
        return "Service updated successfully";
    }


    @Override
    public String deleteService(int serviceId) {
        serviceRepo.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    
        serviceRepo.deleteById(serviceId);
        return "Service deleted successfully";
    }

}
