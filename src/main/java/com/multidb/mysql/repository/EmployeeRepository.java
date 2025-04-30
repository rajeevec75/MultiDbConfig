package com.multidb.mysql.repository;

import com.multidb.mysql.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Using JPQL with @Query annotation
    @Query("SELECT e FROM Employee e WHERE e.departmentId = :departmentId")
    List<Employee> findByDepartmentId(@Param("departmentId") Integer departmentId);

}
