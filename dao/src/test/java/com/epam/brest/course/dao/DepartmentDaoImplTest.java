package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:test-dao.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class DepartmentDaoImplTest {

    private static final String DISTRIBUTION = "Distribution";
    private static final String EDUCATION_AND_TRAINING = "Education and Training";
    private static final String DISTRIBUTION_DEPARTMENT = "Distribution Department";
    private static final String DEPARTMENT_EDUCATION_AND_TRAINING = "Department Education and Training";
    private static final String EDUCATION = "Education";
    private static final String DEPARTMENT_OF_EDUCATION = "Department of Education";
    private static final String NEW_EDUCATION = "NEWEducation";
    private static final String NEW_DEPARTMENT_OF_EDUCATION = "NEW Department of Education";
    private static final String DEPARTMENT_WITH_THE_SAME_NAME = "Department with the same name";

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void getDepartments() {
        List<Department> departments = departmentDao.getDepartments()
                .collect(Collectors.toList());
        assertFalse(departments.isEmpty());
    }

    @Test
    public void getDepartmentById() {
        Department department = departmentDao.getDepartmentById(1).get();
        assertNotNull(department);
        assertTrue(department.getDepartmentId().equals(1));
        assertTrue(department.getDepartmentName().equals(DISTRIBUTION));
        assertTrue(department.getDescription().equals(DISTRIBUTION_DEPARTMENT));
    }

    @Test
    public void addDepartment() {
        List<Department> departments = departmentDao.getDepartments()
                .collect(Collectors.toList());
        int sizeBefore = departments.size();
        Department department = new Department(EDUCATION_AND_TRAINING, DEPARTMENT_EDUCATION_AND_TRAINING);
        int deptId = departmentDao.addDepartment(department);
        assertNotNull(deptId);
        assertTrue((sizeBefore + 1) == departmentDao.getDepartments()
                .collect(Collectors.toList()).size());
    }

    @Test
    public void addSameDepartment() {
        Department department = new Department(EDUCATION_AND_TRAINING, DEPARTMENT_EDUCATION_AND_TRAINING);
        departmentDao.addDepartment(department);
        assertThrows(IllegalArgumentException.class, () -> departmentDao.addDepartment(department));
    }

    @Test
    public void addSameDepartmentWithRule() {
        Department department = new Department(EDUCATION_AND_TRAINING, DEPARTMENT_EDUCATION_AND_TRAINING);
        departmentDao.addDepartment(department);
        assertThrows(IllegalArgumentException.class, () -> departmentDao.addDepartment(department), DEPARTMENT_WITH_THE_SAME_NAME);
    }

    @Test
    public void updateDepartment() {
        Department newDepartment = new Department(EDUCATION, DEPARTMENT_OF_EDUCATION);
        newDepartment.setDepartmentId(departmentDao.addDepartment(newDepartment));
        newDepartment.setDepartmentName(NEW_EDUCATION);
        newDepartment.setDescription(NEW_DEPARTMENT_OF_EDUCATION);
        departmentDao.updateDepartment(newDepartment);
        Department updatedDepartment = departmentDao.getDepartmentById(newDepartment.getDepartmentId()).get();
        assertTrue(newDepartment.getDepartmentId().equals(updatedDepartment.getDepartmentId()));
        assertTrue(newDepartment.getDepartmentName().equals(updatedDepartment.getDepartmentName()));
        assertTrue(newDepartment.getDescription().equals(updatedDepartment.getDescription()));
    }

    @Test
    public void deleteDepartment() {
        Department department = new Department(EDUCATION, DEPARTMENT_OF_EDUCATION);
        int deptId = departmentDao.addDepartment(department);
        List<Department> departments = departmentDao.getDepartments()
                .collect(Collectors.toList());
        int sizeBefore = departments.size();
        departmentDao.deleteDepartmentById(deptId);
        assertTrue((sizeBefore - 1) == departmentDao.getDepartments()
                .collect(Collectors.toList()).size());
    }

}