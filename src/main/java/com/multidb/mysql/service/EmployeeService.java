package com.multidb.mysql.service;

import com.multidb.util.DepartmentWithEmployeeDTO;
import com.multidb.util.EmployeeWithDepartmentDTO;
import java.util.List;

public interface EmployeeService {

    public List<EmployeeWithDepartmentDTO> getAllEmployees();

    public EmployeeWithDepartmentDTO getEmployeeById(Integer employeeId);

    public DepartmentWithEmployeeDTO getDepartmentWithEmployees(Integer departmentId);

}
