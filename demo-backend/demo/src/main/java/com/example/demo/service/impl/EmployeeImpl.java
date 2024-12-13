package com.example.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.LoginMesage;

@Service

public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {

        Employee employee = new Employee(
                employeeDTO.getId(),
                employeeDTO.getUserName(),
                employeeDTO.getEmail(),
                this.passwordEncoder.encode(employeeDTO.getPassword()),
                employeeDTO.getRole()
        );

        employeeRepo.save(employee);

        return employee.getUserName();
    }
    EmployeeDTO employeeDTO;

    @Override
    public LoginMesage loginEmployee(LoginDTO loginDTO) {
        Employee employee = employeeRepo.findByEmail(loginDTO.getEmail());

        if (employee == null) {
            return new LoginMesage("Email not exists", null, 0);
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), employee.getPassword())) {
            return new LoginMesage("Incorrect password", null, 0);
        }

        return new LoginMesage("Login Success", employee.getRole(), employee.getId());
    }

}