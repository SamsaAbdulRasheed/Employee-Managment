package com.example.demo.controller;

import com.example.demo.DTO.DepartmentRequestDTO;
import com.example.demo.DTO.DepartmentResponseDTO;
import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService depetserviService;

    @PostMapping()
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@RequestBody DepartmentRequestDTO dto){
        return new ResponseEntity<> (depetserviService.createDepartment(dto), HttpStatus.CREATED);
    }

    @GetMapping()
    public Map<String,Object> getAllDepartment(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
        Pageable pagable= PageRequest.of(page,size);
        return depetserviService.getAllDepartment(pagable);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@PathVariable Long id,
                                                        @RequestBody DepartmentRequestDTO department){
        return ResponseEntity.ok(depetserviService.updateDepartment(id,department));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id){
        return  depetserviService.deleteDepartment(id);

    }
    @GetMapping("/{id}")
    public Map<String, Object> getDepartmentById(
            @PathVariable("id") Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String expand) {
        Pageable pagable=PageRequest.of(page,size);
        Boolean expandemp = "employee".equalsIgnoreCase(expand);
        return depetserviService.getDepartmentById(id, expandemp,pagable);

    }

}
