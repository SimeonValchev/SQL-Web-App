package com.example.spring_boot;

import com.example.spring_boot.models.Employee;
import java.util.List;

public interface EmployeeDao {

    List<Employee> findAll();

    int insertEmployee(Employee emp);

    int updateEmployee(Employee emp);

    Employee findById(int id);

    int deleteEmployeeById(int id);
}