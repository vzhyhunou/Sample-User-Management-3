package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Value("${employee.select}")
    private String select;

    @Value("${employee.selectById}")
    private String selectById;

    @Value("${employee.selectByDepartmentId}")
    private String selectByDepartmentId;

    @Value("${employee.insert}")
    private String insert;

    @Value("${employee.update}")
    private String update;

    @Value("${employee.delete}")
    private String delete;


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = namedParameterJdbcTemplate.getJdbcOperations()
                .query(select, BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(Integer departmentId) {
        List<Employee> employees = namedParameterJdbcTemplate.getJdbcOperations().query(selectByDepartmentId,
                new Object[]{departmentId}, BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("employeeId", employeeId);
        Employee employee = namedParameterJdbcTemplate.queryForObject(selectById, namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(employee);
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insert, namedParameters, generatedKeyHolder);
        employee.setEmployeeId(generatedKeyHolder.getKey().intValue());
        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(update, namedParameter);
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {
        namedParameterJdbcTemplate.getJdbcOperations().update(delete, employeeId);
    }
}
