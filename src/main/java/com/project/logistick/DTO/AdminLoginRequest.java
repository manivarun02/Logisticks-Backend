package com.project.logistick.DTO;

public class AdminLoginRequest {
    private String empId;  // Changed from email to empId
    private String password;

    // Getters and Setters
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
