package com.example.demo.controller;

import com.example.demo.dto.ServiceDTO;
import com.example.demo.model.Services;
import com.example.demo.service.impl.ComparadorServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/comparador")
@CrossOrigin(origins = "http://localhost:3000")
public class ComparadorController {

    @Autowired
    private ComparadorServicios comparadorServicios;

    @GetMapping("/compare-by-criterion")
    public ResponseEntity<List<ServiceDTO>> compareByCriterion(
        @RequestParam String criterion // "price" o "rating"
    ) {
    List<ServiceDTO> result = comparadorServicios.compareByCriterion(criterion);
    return ResponseEntity.ok(result);
    }

    @GetMapping("/compare-two-services")
    public ResponseEntity<List<ServiceDTO>> compareTwoServices(
        @RequestParam int serviceId1,
        @RequestParam int serviceId2
    ) {
    List<ServiceDTO> result = comparadorServicios.compareTwoServices(serviceId1, serviceId2);
    return ResponseEntity.ok(result);
    }
    @GetMapping("/compare-with-formula")
    public ResponseEntity<Map<String, String>> compareServicesWithFormula(
        @RequestParam int serviceId1,
        @RequestParam int serviceId2
    ) {
    Map<String, String> result = comparadorServicios.compareServicesWithFormula(serviceId1, serviceId2);
    return ResponseEntity.ok(result);
    }

}
