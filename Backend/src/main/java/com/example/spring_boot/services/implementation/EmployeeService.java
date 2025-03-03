package com.example.spring_boot.services.implementation;

import com.example.spring_boot.EmployeeDao;
import com.example.spring_boot.models.Employee;
import com.example.spring_boot.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements Services {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public int insertEmployee(Employee emp) {
        return employeeDao.insertEmployee(emp);
    }

    @Override
    public int updateEmployee(Employee emp) {
        return employeeDao.updateEmployee(emp);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDao.findById(id);
    }

    @Override
    public int deleteEmployeeById(int id) {
        return employeeDao.deleteEmployeeById(id);
    }
}
