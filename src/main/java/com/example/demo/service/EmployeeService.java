package com.example.demo.service;

import com.example.demo.DTO.EmployeeRequestDTO;
import com.example.demo.DTO.EmployeeResponseDTO;
import com.example.demo.Repository.DepartmentRepo;
import com.example.demo.Repository.EmployeeRepo;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        Employee employee = new Employee();

        employee.setName(dto.getName());
        employee.setDob(dto.getDob());
        employee.setSalary(dto.getSalary());
        employee.setAddress(dto.getAddress());
        employee.setRole(dto.getRole());
        employee.setJoiningDate(dto.getJoiningDate());
        employee.setBonusPercentage(dto.getBonusPercentage());

        if (dto.getReportingManagerId() != null) {
            Employee reportingManager = employeeRepo.findById(dto.getReportingManagerId())
                    .orElseThrow(() -> new RuntimeException("not found"));
            employee.setReportingManager(reportingManager);
        }
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("not found"));
            employee.setDepartment(department);
        }

        Employee emp = employeeRepo.save(employee);

        EmployeeResponseDTO response = new EmployeeResponseDTO();
        response.setId(emp.getId());
        response.setName(emp.getName());
        response.setDob(emp.getDob());
        response.setSalary(emp.getSalary());
        response.setAddress(emp.getAddress());
        response.setRole(emp.getRole());
        response.setJoiningDate(emp.getJoiningDate());
        response.setBonusPercentage(emp.getBonusPercentage());

        if (emp.getReportingManager() != null) {
            response.setReportingManagerName(emp.getReportingManager().getId());
        }
        if (emp.getDepartment() != null) {
            response.setDepartmentId(emp.getDepartment().getId());
        }
        return response;
    }

    public Map<String,Object> getAllEmployee(Pageable pagable) {
        Page<Employee> employees = employeeRepo.findAll(pagable);
        List<EmployeeResponseDTO> responseList = new ArrayList<>();

        for (Employee emp : employees.getContent()) {
            EmployeeResponseDTO dto = new EmployeeResponseDTO();
            dto.setId(emp.getId());
            dto.setName(emp.getName());
            dto.setDob(emp.getDob());
            dto.setJoiningDate(emp.getJoiningDate());
            dto.setAddress(emp.getAddress());
            dto.setRole(emp.getRole());
            dto.setBonusPercentage(emp.getBonusPercentage());
            dto.setSalary(emp.getSalary());

            if (emp.getReportingManager() != null) {
                dto.setReportingManagerName(emp.getReportingManager().getId());
            }

            if (emp.getDepartment() != null) {
                dto.setDepartmentId(emp.getDepartment().getId());
            }
            responseList.add(dto);



        }
            Map<String,Object> map=new HashMap<>();
            map.put("employees", responseList);
            map.put("CurrentPage", employees.getNumber());
            map.put("TotalPage",employees.getTotalPages());
            map.put("totalItem",employees.getTotalElements());
        return map;
    }

    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        employee.setName(dto.getName());
        employee.setDob(dto.getDob());
        employee.setAddress(dto.getAddress());
        employee.setBonusPercentage(dto.getBonusPercentage());
        employee.setRole(dto.getRole());
        employee.setSalary(dto.getSalary());
        employee.setJoiningDate(dto.getJoiningDate());
        if (dto.getReportingManagerId() != null) {
            Employee emp = employeeRepo.findById(dto.getReportingManagerId()).orElse(null);
            employee.setReportingManager(emp);
        }
        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepo.findById(dto.getDepartmentId()).orElse(null);
            employee.setDepartment(dept);
        }
        Employee update = employeeRepo.save(employee);

        EmployeeResponseDTO response = new EmployeeResponseDTO();
        response.setId(update.getId());
        response.setName(update.getName());
        response.setDob(update.getDob());
        response.setSalary(update.getSalary());
        response.setRole(update.getRole());
        response.setBonusPercentage(update.getBonusPercentage());
        response.setAddress(update.getAddress());
        response.setJoiningDate(update.getJoiningDate());
        if (update.getReportingManager() != null) {
            response.setReportingManagerName(update.getReportingManager().getId());
        }
        if (update.getDepartment() != null) {
            response.setDepartmentId(update.getDepartment().getId());
        }

        return response;
    }

    public  Map<String,Object> getEmployeeAndId(Pageable pagable) {

        Page<Employee> employee = employeeRepo.findAll(pagable);

        List<Map<Long,String>> listEmployee = new ArrayList<>();
        for (Employee emp : employee.getContent()) {
            HashMap<Long,String> map=new HashMap<>();
            map.put(emp.getId(),emp.getName());
            listEmployee.add(map);
        }
        Map<String,Object> response=new HashMap<>();
        response.put("Employees",listEmployee);
        response.put("CurrentPage", employee.getNumber());
        response.put("TotalPage",employee.getTotalPages());
        response.put("totalItem",employee.getTotalElements());
        return response;
    }

    public EmployeeResponseDTO updateEmployeeDepartment(Long empId, Long deptId) {
        Employee emp=employeeRepo.findById(empId).orElse(null);
        Department dept=departmentRepo.findById(deptId).orElseThrow();
        emp.setDepartment(dept);

        Employee update= employeeRepo.save(emp);
        EmployeeResponseDTO response = new EmployeeResponseDTO();
        response.setId(update.getId());
        response.setName(update.getName());
        response.setDob(update.getDob());
        response.setSalary(update.getSalary());
        response.setRole(update.getRole());
        response.setBonusPercentage(update.getBonusPercentage());
        response.setAddress(update.getAddress());
        response.setJoiningDate(update.getJoiningDate());
        if (update.getReportingManager() != null) {
            response.setReportingManagerName(update.getReportingManager().getId());
        }
            response.setDepartmentId(update.getDepartment().getId());

return response;
    }
}
