package com.multidb.util;

import com.multidb.mysql.model.Employee;
import com.multidb.postgresql.model.Department;

public class DataResponse {

    private Employee employee;
    private Department department;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
