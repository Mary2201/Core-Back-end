package com.example.demo.service.impl;

import com.example.demo.dto.ServiceDTO;
import com.example.demo.model.Services;
import com.example.demo.repository.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ComparadorServicios {
    @Autowired
    private ServiceRepo serviceRepository;

    public List<ServiceDTO> compareByCriterion(String criterion) {
    List<Services> services = serviceRepository.findAll();

    Comparator<Services> comparator;
    if ("price".equalsIgnoreCase(criterion)) {
        comparator = Comparator.comparingDouble(Services::getPrice);
    } else if ("rating".equalsIgnoreCase(criterion)) {
        comparator = Comparator.comparingDouble(Services::getAverageRating).reversed();
    } else {
        throw new IllegalArgumentException("Criterio no válido: " + criterion);
    }

    return services.stream()
            .sorted(comparator)
            .map(service -> new ServiceDTO(
                    service.getId(),
                    service.getServiceName(),
                    service.getDescription(),
                    service.getPrice(),
                    service.getEmployee() != null ? service.getEmployee().getId() : null,
                    null,
                    service.getAverageRating()
            ))
            .collect(Collectors.toList());
    }

    public List<ServiceDTO> compareTwoServices(int serviceId1, int serviceId2) {
    Services service1 = serviceRepository.findById(serviceId1)
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado: " + serviceId1));
    Services service2 = serviceRepository.findById(serviceId2)
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado: " + serviceId2));

    return List.of(
            new ServiceDTO(
                    service1.getId(),
                    service1.getServiceName(),
                    service1.getDescription(),
                    service1.getPrice(),
                    service1.getEmployee() != null ? service1.getEmployee().getId() : null,
                    null,
                    service1.getAverageRating()
            ),
            new ServiceDTO(
                    service2.getId(),
                    service2.getServiceName(),
                    service2.getDescription(),
                    service2.getPrice(),
                    service2.getEmployee() != null ? service2.getEmployee().getId() : null,
                    null,
                    service2.getAverageRating()
            )
        );
    }
    public Map<String, String> compareServicesWithFormula(int serviceId1, int serviceId2) {
        if (serviceId1 == serviceId2) {
            throw new IllegalArgumentException("No puedes comparar un servicio consigo mismo.");
        }
        Services service1 = serviceRepository.findById(serviceId1)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado: " + serviceId1));
        Services service2 = serviceRepository.findById(serviceId2)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado: " + serviceId2));
    
        double rating1 = service1.getAverageRating();
        double rating2 = service2.getAverageRating();
    
        if (rating1 == 0.0 && rating2 == 0.0) {
            throw new RuntimeException("Ambos servicios no tienen calificaciones.");
        }
        if (rating1 == 0.0) {
            throw new RuntimeException("El servicio \"" + service1.getServiceName() + "\" no tiene calificaciones.");
        }
        if (rating2 == 0.0) {
            throw new RuntimeException("El servicio \"" + service2.getServiceName() + "\" no tiene calificaciones.");
        }
    

        String result;
        double percentage;
    
        if (rating1 > rating2) {
            percentage = (rating1 / (rating1 + rating2)) * 100;
            result = "El servicio \"" + service1.getServiceName() + "\" tiene un " +
                     String.format("%.2f", percentage) +
                     "% de las calificaciones totales frente a \"" + service2.getServiceName() + "\".";
        } else if (rating2 > rating1) {
            percentage = (rating2 / (rating1 + rating2)) * 100;
            result = "El servicio \"" + service2.getServiceName() + "\" tiene un " +
                     String.format("%.2f", percentage) +
                     "% de las calificaciones totales frente a \"" + service1.getServiceName() + "\".";
        } else {
            result = "Ambos servicios tienen la misma calificación. Cada uno representa el 50% de las calificaciones totales.";
            percentage = 50.0;
        }
    
        // Construir el resultado
        Map<String, String> response = new HashMap<>();
        response.put("service1", service1.getServiceName());
        response.put("service2", service2.getServiceName());
        response.put("result", result);
        response.put("percentage", String.format("%.2f", percentage));
    
        return response;
    }
    
}
