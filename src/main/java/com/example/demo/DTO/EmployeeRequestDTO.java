package com.example.demo.DTO;

import java.time.LocalDate;

public class EmployeeRequestDTO {

    private String name;
    private LocalDate dob;
    private Double salary;
    private Long departmentId;
    private String address;
    private String role;
    private LocalDate joiningDate;
    private Double bonusPercentage;
    private Long reportingManagerId;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Double getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(Double bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }

    public Long getReportingManagerId() {
        return reportingManagerId;
    }

    public void setReportingManagerId(Long reportingManagerId) {
        this.reportingManagerId = reportingManagerId;
    }
}

