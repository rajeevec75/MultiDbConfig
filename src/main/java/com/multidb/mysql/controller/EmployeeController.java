package com.multidb.mysql.controller;

import com.multidb.mysql.service.EmployeeService;
import com.multidb.util.DepartmentWithEmployeeDTO;
import com.multidb.util.EmployeeWithDepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET /employees
    @GetMapping("/list")
    public List<EmployeeWithDepartmentDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeWithDepartmentDTO> getEmployeeById(@PathVariable Integer employeeId) {
        EmployeeWithDepartmentDTO employeeDTO = employeeService.getEmployeeById(employeeId);

        if (employeeDTO != null) {
            return ResponseEntity.ok(employeeDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to get department with employees by departmentId
    @GetMapping()
    public ResponseEntity<DepartmentWithEmployeeDTO> getDepartmentWithEmployees(@RequestParam Integer departmentId) {
        DepartmentWithEmployeeDTO departmentWithEmployees = employeeService.getDepartmentWithEmployees(departmentId);

        // If department and employees exist, return with status OK
        if (departmentWithEmployees != null) {
            return ResponseEntity.ok(departmentWithEmployees);
        }

        // If no department is found, return NOT_FOUND status
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
