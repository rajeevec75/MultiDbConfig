package com.multidb.mysql.service.impl;

import com.multidb.mysql.model.Employee;
import com.multidb.mysql.repository.EmployeeRepository;
import com.multidb.mysql.service.EmployeeService;
import com.multidb.postgresql.model.Department;
import com.multidb.postgresql.service.DepartmentService;
import com.multidb.util.DepartmentWithEmployeeDTO;
import com.multidb.util.EmployeeWithDepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    @Override
    public List<EmployeeWithDepartmentDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employee -> {
                    Optional<Department> departmentOptional = departmentService.getDepartmentById(employee.getDepartmentId());

                    return departmentOptional.map(department -> {
                        EmployeeWithDepartmentDTO dto = new EmployeeWithDepartmentDTO();
                        dto.setEmployeeId(employee.getEmployeeId());
                        dto.setFirstName(employee.getFirstName());
                        dto.setLastName(employee.getLastName());
                        dto.setEmail(employee.getEmail());
                        dto.setPhoneNumber(employee.getPhoneNumber());
                        dto.setDesignation(employee.getDesignation());
                        dto.setSalary(employee.getSalary());
                        dto.setDateOfJoining(employee.getDateOfJoining());
                        dto.setStatus(employee.getStatus());
                        dto.setDepartment(department); // nested department
                        return dto;
                    }).orElse(null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeWithDepartmentDTO getEmployeeById(Integer employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            Optional<Department> departmentOptional = departmentService.getDepartmentById(employee.getDepartmentId());

            if (departmentOptional.isPresent()) {
                Department department = departmentOptional.get();

                EmployeeWithDepartmentDTO dto = new EmployeeWithDepartmentDTO();
                dto.setEmployeeId(employee.getEmployeeId());
                dto.setFirstName(employee.getFirstName());
                dto.setLastName(employee.getLastName());
                dto.setEmail(employee.getEmail());
                dto.setPhoneNumber(employee.getPhoneNumber());
                dto.setDesignation(employee.getDesignation());
                dto.setSalary(employee.getSalary());
                dto.setDateOfJoining(employee.getDateOfJoining());
                dto.setStatus(employee.getStatus());
                dto.setDepartment(department);  // nested

                return dto;
            }
        }

        return null; // Or throw exception: EmployeeNotFoundException or DepartmentNotFoundException
    }

    @Override
    public DepartmentWithEmployeeDTO getDepartmentWithEmployees(Integer departmentId) {
        // Retrieve the department by ID from the department service
        Optional<Department> departmentOptional = this.departmentService.getDepartmentById(departmentId);

        // Check if department exists
        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();

            // Fetch the list of employees associated with this department
            List<Employee> employeeList = this.employeeRepository.findByDepartmentId(department.getDepartmentId());

            // Create a new DTO and set the data
            DepartmentWithEmployeeDTO dto = new DepartmentWithEmployeeDTO();
            dto.setDepartmentId(department.getDepartmentId());
            dto.setDepartmentName(department.getDepartmentName());
            dto.setDescription(department.getDescription());
            dto.setLocation(department.getLocation());
            dto.setCreatedAt(department.getCreatedAt());
            dto.setUpdatedAt(department.getUpdatedAt());
            dto.setEmployees(employeeList); // Set the list of employees

            // Return the populated DTO
            return dto;
        }

        // Return null or throw an exception if department is not found
        return null;
    }

}
