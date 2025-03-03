package com.example.spring_boot.services;

import com.example.spring_boot.models.Employee;

import java.util.List;

public interface Services {

    List<Employee> getAllEmployees();

    int insertEmployee(Employee emp);

    int updateEmployee(Employee emp);

    Employee getEmployeeById(int id);

    int deleteEmployeeById(int id);
}
