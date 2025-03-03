package com.example.spring_boot.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class Employee {

    @Schema(description = "The user's ID number", example = "42")
    @Min(value = 0, message = "ID must be positive")
    private int id;

    @Schema(description = "The user's first name", example = "John")
    @NotBlank(message = "First name must not be blank")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    private String first_name;

    @Schema(description = "The user's last name", example = "Doe")
    @NotBlank(message = "Last name must not be blank")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String last_name;

    @NotNull
    @Schema(description = "The user's salary", example = "2000.00")
    @Positive(message = "Salary must be a positive number")
    private double salary;

    @NotNull
    @Schema(description = "The user's starting date", example = "2020-01-06")
    @Past(message = "Start date must be in the past")
    private Date start_date;


    public Employee(int id, String last_name, String first_name, double salary, Date start_date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.salary = salary;
        this.start_date = start_date;
    }

    public Employee(String first_name, String last_name, double salary, Date start_date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.salary = salary;
        this.start_date = start_date;
    }

    public Employee(){

    }

    public int getId() {
        return id;
    }

    //HOW IS THIS USED???
    public void setId(int id) { this.id = id; }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }
}
