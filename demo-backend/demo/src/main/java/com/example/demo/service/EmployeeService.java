package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.LoginDTO;

public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDTO);
    LoginMesage loginEmployee(LoginDTO loginDTO);
}
