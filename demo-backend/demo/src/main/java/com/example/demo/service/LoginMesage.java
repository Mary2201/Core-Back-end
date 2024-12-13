package com.example.demo.service;

public class LoginMesage {
    private String message;
    private String role;
    private int employeeId;

    public LoginMesage(String message, String role, int employeeId) {
        this.message = message;
        this.role = role;
        this.employeeId = employeeId;
    }

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
