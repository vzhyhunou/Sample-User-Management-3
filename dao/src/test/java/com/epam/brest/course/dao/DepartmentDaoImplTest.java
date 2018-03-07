package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
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
        List<Department> departments = departmentDao.getDepartments();
        Assert.assertFalse(departments.isEmpty());
    }

    @Test
    public void getDepartmentById() {
        Department department = departmentDao.getDepartmentById(1);
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(1));
        Assert.assertTrue(department.getDepartmentName().equals(DISTRIBUTION));
        Assert.assertTrue(department.getDescription().equals(DISTRIBUTION_DEPARTMENT));
    }

    @Test
    public void addDepartment() {
        List<Department> departments = departmentDao.getDepartments();
        int sizeBefore = departments.size();
        Department department = new Department(EDUCATION_AND_TRAINING, DEPARTMENT_EDUCATION_AND_TRAINING);
        Department newDepartment = departmentDao.addDepartment(department);
        Assert.assertNotNull(newDepartment.getDepartmentId());
        Assert.assertTrue(newDepartment.getDepartmentName().equals(department.getDepartmentName()));
        Assert.assertTrue(newDepartment.getDescription().equals(department.getDescription()));
        Assert.assertTrue((sizeBefore + 1) == departmentDao.getDepartments().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addSameDepartment() {
        Department department = new Department(EDUCATION_AND_TRAINING, DEPARTMENT_EDUCATION_AND_TRAINING);
        departmentDao.addDepartment(department);
        departmentDao.addDepartment(department);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addSameDepartmentWithRule() {
        Department department = new Department(EDUCATION_AND_TRAINING, DEPARTMENT_EDUCATION_AND_TRAINING);
        departmentDao.addDepartment(department);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(DEPARTMENT_WITH_THE_SAME_NAME);
        departmentDao.addDepartment(department);
    }

    @Test
    public void updateDepartment() {
        Department department = new Department(EDUCATION, DEPARTMENT_OF_EDUCATION);
        Department newDepartment = departmentDao.addDepartment(department);
        newDepartment.setDepartmentName(NEW_EDUCATION);
        newDepartment.setDescription(NEW_DEPARTMENT_OF_EDUCATION);
        departmentDao.updateDepartment(newDepartment);
        Department updatedDepartment = departmentDao.getDepartmentById(newDepartment.getDepartmentId());
        Assert.assertTrue(newDepartment.getDepartmentId().equals(updatedDepartment.getDepartmentId()));
        Assert.assertTrue(newDepartment.getDepartmentName().equals(updatedDepartment.getDepartmentName()));
        Assert.assertTrue(newDepartment.getDescription().equals(updatedDepartment.getDescription()));
    }

    @Test
    public void deleteDepartment() {
        Department department = new Department(EDUCATION, DEPARTMENT_OF_EDUCATION);
        department = departmentDao.addDepartment(department);
        List<Department> departments = departmentDao.getDepartments();
        int sizeBefore = departments.size();
        departmentDao.deleteDepartmentById(department.getDepartmentId());
        Assert.assertTrue((sizeBefore - 1) == departmentDao.getDepartments().size());
    }

}