package com.example.demo.DTO;

import com.example.demo.model.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DepartmentResponseDTO {

    private Long id;

    private String name;

    private LocalDate createdDate;

    private Long departmentHead;

    private List<EmployeeResponseDTO> employees = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(Long departmentHead) {
        this.departmentHead = departmentHead;
    }

    public List<EmployeeResponseDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResponseDTO> employees) {
        this.employees = employees;
    }
}
