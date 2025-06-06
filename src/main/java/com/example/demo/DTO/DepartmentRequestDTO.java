package com.example.demo.DTO;



import java.time.LocalDate;


public class DepartmentRequestDTO {


    private String name;

    private LocalDate createdDate;

    private Long departmentHead;

//    private List<Integer> employees = new ArrayList<>();



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


}
