package com.multidb.postgresql.service.impl;

import com.multidb.postgresql.model.Department;
import com.multidb.postgresql.repository.DepartmentRepository;
import com.multidb.postgresql.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(Integer departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Integer departmentId, Department departmentDetails) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        department.setDepartmentName(departmentDetails.getDepartmentName());
        department.setDescription(departmentDetails.getDescription());
        department.setLocation(departmentDetails.getLocation());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        departmentRepository.deleteById(departmentId);
    }

}
