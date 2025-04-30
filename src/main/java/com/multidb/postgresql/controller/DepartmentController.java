package com.multidb.postgresql.controller;

import com.multidb.postgresql.model.Department;
import com.multidb.postgresql.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    // Constructor Injection
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Get all departments
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // Get department by ID
    @GetMapping("/{id}")
    public Optional<Department> getDepartmentById(@PathVariable("id") Integer departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    // Create a new department
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    // Update an existing department
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable("id") Integer departmentId, @RequestBody Department departmentDetails) {
        return departmentService.updateDepartment(departmentId, departmentDetails);
    }

    // Delete a department by ID
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable("id") Integer departmentId) {
        departmentService.deleteDepartment(departmentId);
    }
}
