package com.example.demo.service;

import com.example.demo.DTO.DepartmentRequestDTO;
import com.example.demo.DTO.DepartmentResponseDTO;
import com.example.demo.DTO.EmployeeResponseDTO;
import com.example.demo.Repository.DepartmentRepo;
import com.example.demo.Repository.EmployeeRepo;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {

    @Autowired
    private EmployeeRepo empRepo;

    @Autowired
    private DepartmentRepo deptRepo;

    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto) {

        Department dept=new Department();

        dept.setName(dto.getName());
        dept.setCreatedDate(dto.getCreatedDate());

        if(dto.getDepartmentHead() != null) {

            Employee emp = empRepo.findById(dto.getDepartmentHead())
                    .orElseThrow(()-> new RuntimeException("not Found"));
            dept.setDepartmentHead(emp);
        }

        Department saved= deptRepo.save(dept);

        DepartmentResponseDTO response=new DepartmentResponseDTO();

        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setCreatedDate(saved.getCreatedDate());

        if(saved.getDepartmentHead() !=null){
            response.setDepartmentHead(saved.getDepartmentHead().getId());
        }

//        if(saved.getEmployees() !=null){
//            response.setEmployees(saved.getEmployees().stream()
//                    .map(employee -> employee.getId()).collect(Collectors.toList()));
//        }
        return response;

    }

    public  Map<String,Object> getAllDepartment(Pageable pagable) {
        Page<Department> department= deptRepo.findAll(pagable);
        List<DepartmentResponseDTO>  responesList=new ArrayList<>();

        for(Department dept: department.getContent()){
            DepartmentResponseDTO dto= new DepartmentResponseDTO();
            dto.setId(dept.getId());
            dto.setName(dept.getName());
            dto.setCreatedDate(dept.getCreatedDate());

            if(dept.getDepartmentHead() !=null) {
                dto.setDepartmentHead(dept.getDepartmentHead().getId());
            }

            responesList.add(dto);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("Departments",responesList);
        map.put("CurrentPage", department.getNumber());
        map.put("TotalPage",department.getTotalPages());
        map.put("totalItem",department.getTotalElements());
        return map;
    }

    public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO department) {
       Department dept= deptRepo.findById(id).orElseThrow();
       dept.setName(department.getName());
       dept.setCreatedDate(department.getCreatedDate());

       Employee employee= empRepo.findById(department.getDepartmentHead()).orElseThrow();
       dept.setDepartmentHead(employee);

       Department saved= deptRepo.save(dept);
       DepartmentResponseDTO response=new DepartmentResponseDTO();

       response.setId(saved.getId());
       response.setName(saved.getName());
       response.setCreatedDate(saved.getCreatedDate());

       if(saved.getDepartmentHead() !=null){
            response.setDepartmentHead(saved.getDepartmentHead().getId());
       }
       return  response;
    }

    public ResponseEntity<String> deleteDepartment(Long id) {
       Department department= deptRepo.findById(id).orElseThrow(()-> new RuntimeException("department not found"));
       if(! department.getEmployees().isEmpty()) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                 .body("Cannot delete department with assigned employees");
       }
       else {
           deptRepo.deleteById(id);
           return ResponseEntity.ok().body("deleted successfully");
       }
    }

    public Map<String, Object> getDepartmentById(Long id, Boolean expandemp, Pageable pageable) {
        Department department= deptRepo.findById(id).orElseThrow();

        DepartmentResponseDTO dto= new DepartmentResponseDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setCreatedDate(department.getCreatedDate());

        if(department.getDepartmentHead() !=null) {
            dto.setDepartmentHead(department.getDepartmentHead().getId());
        }
        Page<Employee> employee= empRepo.findByDepartmentId(id,pageable);

        if(expandemp){

            List<EmployeeResponseDTO> empployeeList=new ArrayList<>();
            for(Employee emp: employee.getContent()){
                EmployeeResponseDTO empdto=new EmployeeResponseDTO();
                empdto.setId(emp.getId());
                empdto.setName(emp.getName());
                empdto.setDob(emp.getDob());
                empdto.setSalary(emp.getSalary());
                empdto.setAddress(emp.getAddress());
                empdto.setRole(emp.getRole());
                empdto.setJoiningDate(emp.getJoiningDate());
                empdto.setBonusPercentage(emp.getBonusPercentage());
                empdto.setDepartmentId(emp.getDepartment().getId());
                empdto.setReportingManagerName(emp.getReportingManager().getId());
                empployeeList.add(empdto);
            }
             dto.setEmployees(empployeeList);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("Deparments",dto);
        map.put("CurrentPage", employee.getNumber());
        map.put("TotalPage",employee.getTotalPages());
        map.put("totalItem",employee.getTotalElements());
        return map;
    }


}
