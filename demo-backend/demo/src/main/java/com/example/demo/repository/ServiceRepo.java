package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Services;

@EnableJpaRepositories
@Repository
public interface ServiceRepo extends JpaRepository<Services, Integer> {

    // Métodos adicionales si necesitas búsquedas específicas

    // Encuentra servicios por nombre (opcional, por si deseas búsquedas)
    //List<Services> findByServiceNameContainingIgnoreCase(String serviceName);

    // Encuentra servicios asociados a un empleado específico
    List<Services> findByEmployeeId(int employeeId);
}

