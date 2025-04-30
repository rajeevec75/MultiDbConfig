package com.multidb.postgresql.service;

import com.multidb.postgresql.model.Department;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Optional<Department> getDepartmentById(Integer departmentId);

    Department createDepartment(Department department);

    Department updateDepartment(Integer departmentId, Department departmentDetails);

    void deleteDepartment(Integer departmentId);

}
