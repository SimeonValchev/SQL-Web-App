package com.example.spring_boot.controllers;



import com.example.spring_boot.models.Employee;
import com.example.spring_boot.services.Services;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/employees")
@Tag(name = "Employee Controller", description = "Endpoints for managing employees")
public class EmployeeController {

    @Autowired
    Services employeeService;


    @GetMapping
    @Operation(summary = "Requests all employees", description = "Returns a list of all employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {

            List<Employee> employees = new ArrayList<>(employeeService.getAllEmployees());

            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (DataAccessException e) {
            System.out.println("Exception accessing data: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    @Operation(summary = "Requests an employee by ID", description = "Returns the employee with given ID, if they exist in the database")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") @Min(1) int id) {

        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }


    @PostMapping
    @Operation(summary = "Insert a new employee", description = "Adds a new employee to the database")
    public ResponseEntity<String> insertEmployee(@Valid @RequestBody Employee employee) {
        try {
            employeeService.insertEmployee(employee);
            return new ResponseEntity<>("Employee was inserted.", HttpStatus.CREATED);

        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Exception accessing database: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update employee", description = "Updates the employee with matching ID")
    public ResponseEntity<String> updateTutorial(@PathVariable("id") @Min(1) int id,@Valid @RequestBody Employee employee) {
        try {
            Employee _employee = employeeService.getEmployeeById(id);

            if (_employee != null) {
                _employee.setFirst_name(employee.getFirst_name());
                _employee.setLast_name(employee.getLast_name());
                _employee.setSalary(employee.getSalary());
                _employee.setStart_date(employee.getStart_date());

                employeeService.updateEmployee(_employee);
                return new ResponseEntity<>("Employee was updated successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Employee with id = " + id + " does not exist!", HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Exception accessing database: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes an employee", description = "Deletes the employee with matching ID")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") @Min(1) int id) {
        try {
            int result = employeeService.deleteEmployeeById(id);
            if (result == 0) {
                return new ResponseEntity<>("Employee with id = " + id + " does not exist!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Employee with id = " + id + " was deleted successfully.", HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Exception accessing database: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
