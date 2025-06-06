package com.example.demo.Repository;

import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);
}
