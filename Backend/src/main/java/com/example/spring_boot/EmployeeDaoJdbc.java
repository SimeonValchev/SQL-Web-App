package com.example.spring_boot;

import com.example.spring_boot.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDaoJdbc implements EmployeeDao{

    private static final String INSERT_SQL = "INSERT INTO employees (first_name, last_name, salary, start_date) VALUES(?,?,?,?)";
    private static final String SELECTALL_SQL = "SELECT id, first_name, last_name, salary, start_date FROM employees";
    private static final String UPDATE_SQL = "UPDATE employees SET first_name = ?, last_name = ?, salary = ?, start_date = ? WHERE id = ?";
    private static final String SELECTID_SQL = "SELECT id, first_name, last_name, salary, start_date FROM employees WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM employees WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertEmployee(Employee emp) {
        return jdbcTemplate.update(INSERT_SQL,
                emp.getFirst_name(), emp.getLast_name(), emp.getSalary(), emp.getStart_date());
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> aa = jdbcTemplate.query(SELECTALL_SQL, BeanPropertyRowMapper.newInstance(Employee.class));
        return aa;
    }

    @Override
    public int updateEmployee(Employee emp) {
        return jdbcTemplate.update(UPDATE_SQL,
                emp.getFirst_name(), emp.getLast_name(), emp.getSalary(), emp.getStart_date(), emp.getId());
    }

    @Override
    public Employee findById(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECTID_SQL,
                    BeanPropertyRowMapper.newInstance(Employee.class), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteEmployeeById(int id) {
        return jdbcTemplate.update(DELETE_SQL, id);
    }
}
