package com.example.demo.DTO;

import java.time.LocalDate;

public class EmployeeResponseDTO {


        private Long id;
        private LocalDate dob;
        private String name;
        private String role;
        private Double salary;
        private String address;
        private Double bonusPercentage;
        private Long reportingManagerName;
        private LocalDate joiningDate;
        private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(Double bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }

    public Long getReportingManagerName() {
        return reportingManagerName;
    }

    public void setReportingManagerName(Long reportingManagerName) {
        this.reportingManagerName = reportingManagerName;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
