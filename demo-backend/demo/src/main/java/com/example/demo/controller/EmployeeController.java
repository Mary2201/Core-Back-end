package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.LoginMesage;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping(path = "/save")
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        String id = employeeService.addEmployee(employeeDTO);
    return ResponseEntity.ok("Employee registered with ID: " + id);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<LoginMesage> loginEmployee(@RequestBody LoginDTO loginDTO) {
        LoginMesage loginResponse = employeeService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
    /*@PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO) {
    LoginMesage loginResponse = employeeService.loginEmployee(loginDTO);
    
    // Verificar si el login es exitoso
    if ("Login Success".equals(loginResponse.getMessage())) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login Success");
        response.put("token", "some-generated-token"); // Reemplaza con la lógica de generación de token real
        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
    }
    }*/


}