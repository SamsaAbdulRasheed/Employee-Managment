package com.example.demo.controller;
import com.example.demo.DTO.EmployeeRequestDTO;
import com.example.demo.DTO.EmployeeResponseDTO;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
    @RequestMapping("/employees")
    public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


        @PostMapping()
        public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO dto) {
            return new ResponseEntity<>(employeeService.createEmployee(dto), HttpStatus.CREATED) ;
        }
        @PutMapping("/{emp_id}/dept/{dept_id}")
        public ResponseEntity<EmployeeResponseDTO> updateEmployeeDepartment(
                @PathVariable("emp_id") Long emp_id,
                @PathVariable("dept_id")Long dept_Id){
            return ResponseEntity.ok(employeeService.updateEmployeeDepartment(emp_id,dept_Id));
        }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDTO dto) {
        EmployeeResponseDTO updated = employeeService.updateEmployee(id, dto);
        return ResponseEntity.ok(updated);
    }


        @GetMapping()
        public Map<String,Object> getAllEmployees(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "20") int size) {
            Pageable pageable = PageRequest.of(page, size);

            return employeeService.getAllEmployee(pageable);
        }

        @GetMapping("/lookup")
        public ResponseEntity<?> getLookup(@RequestParam(value = "lookup",required = false) Boolean lookup,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "20") int size) {
            Pageable pageable = PageRequest.of(page, size);

            if(lookup !=null & Boolean.TRUE.equals(lookup)) {
                return ResponseEntity.ok(employeeService.getEmployeeAndId(pageable));
            }
            else {
                return ResponseEntity.ok(employeeService.getAllEmployee(pageable));
            }
        }
}
