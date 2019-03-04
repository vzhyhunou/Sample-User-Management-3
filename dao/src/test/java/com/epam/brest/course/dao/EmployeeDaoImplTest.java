package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:test-dao.xml", "classpath:dao.xml"})
@Rollback
@Transactional(transactionManager = "transactionManager")
public class EmployeeDaoImplTest {

    private static final String VASIA = "Vasia";
    private static final String PETIA = "Petia";
    private static final String NEW_VASIA = "NEW Vasia";
    private static final int SALARY_1000 = 1000;
    private static final int SALARY_2000 = 2000;

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void getEmployees() throws Exception {
        List<Employee> employees = employeeDao.getEmployees();
        assertTrue(employees.isEmpty());
    }

    @Test
    public void getEmployeesByDepartmentId() throws Exception {
        Employee employee1 = new Employee(VASIA, SALARY_1000, 1);
        employeeDao.addEmployee(employee1);
        Employee employee2 = new Employee(PETIA, SALARY_2000, 1);
        employeeDao.addEmployee(employee2);
        List<Employee> employees = employeeDao.getEmployeesByDepartmentId(1);
        assertNotNull(employeeDao);
        assertTrue(employees.size() == 2);
    }

    @Test
    public void getEmployeeById() throws Exception {
        Employee employee = new Employee(VASIA, SALARY_1000, 1);
        employee = employeeDao.addEmployee(employee);
        Employee newEmployee = employeeDao.getEmployeeById(employee.getEmployeeId());
        assertNotNull(employeeDao);
        assertTrue(newEmployee.getEmployeeId().equals(employee.getEmployeeId()));
        assertTrue(newEmployee.getEmployeeName().equals(employee.getEmployeeName()));
        assertTrue(newEmployee.getSalary().equals(employee.getSalary()));
        assertTrue(newEmployee.getDepartmentId().equals(employee.getDepartmentId()));
    }

    @Test
    public void addEmployee() throws Exception {
        List<Employee> employees = employeeDao.getEmployees();
        int sizeBefore = employees.size();
        Employee employee = new Employee(VASIA, SALARY_1000, 1);
        Employee newEmployee = employeeDao.addEmployee(employee);
        assertNotNull(newEmployee.getEmployeeId());
        assertTrue(newEmployee.getEmployeeName().equals(employee.getEmployeeName()));
        assertTrue(newEmployee.getSalary().equals(employee.getSalary()));
        assertTrue(newEmployee.getDepartmentId().equals(employee.getDepartmentId()));
        assertTrue((sizeBefore + 1) == employeeDao.getEmployees().size());
    }

    @Test
    public void updateEmployee() throws Exception {
        Employee employee = new Employee(VASIA, SALARY_1000, 1);
        Employee newEmployee = employeeDao.addEmployee(employee);
        newEmployee.setEmployeeName(NEW_VASIA);
        newEmployee.setSalary(SALARY_2000);
        employeeDao.updateEmployee(newEmployee);
        Employee updatedEmployee = employeeDao.getEmployeeById(newEmployee.getEmployeeId());
        assertTrue(updatedEmployee.getEmployeeId().equals(newEmployee.getEmployeeId()));
        assertTrue(updatedEmployee.getEmployeeName().equals(newEmployee.getEmployeeName()));
        assertTrue(updatedEmployee.getSalary().equals(newEmployee.getSalary()));
        assertTrue(updatedEmployee.getDepartmentId().equals(newEmployee.getDepartmentId()));
    }

    @Test
    public void deleteEmployeeById() throws Exception {
        Employee employee = new Employee(VASIA, SALARY_1000, 1);
        employee = employeeDao.addEmployee(employee);
        List<Employee> employees = employeeDao.getEmployees();
        int sizeBefore = employees.size();
        employeeDao.deleteEmployeeById(employee.getEmployeeId());
        assertTrue((sizeBefore - 1) == employeeDao.getEmployees().size());
    }

}
