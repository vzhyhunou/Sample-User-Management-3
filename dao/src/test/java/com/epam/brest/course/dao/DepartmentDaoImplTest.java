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
import java.util.stream.Collectors;

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
        List<Department> departments = departmentDao.getDepartments()
                .collect(Collectors.toList());
        Assert.assertFalse(departments.isEmpty());
    }

    @Test
    public void getDepartmentById() {
        Department department = departmentDao.getDepartmentById(1).get();
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(1));
        Assert.assertTrue(department.getDepartmentName().equals(DISTRIBUTION));
        Assert.assertTrue(department.getDescription().equals(DISTRIBUTION_DEPARTMENT));
    }

    @Test
    public void addDepartment() {
        List<Department> departments = departmentDao.getDepartments()
                .collect(Collectors.toList());
        int sizeBefore = departments.size();
        Department department = new Department(EDUCATION_AND_TRAINING, DEPARTMENT_EDUCATION_AND_TRAINING);
        int deptId = departmentDao.addDepartment(department);
        Assert.assertNotNull(deptId);
        Assert.assertTrue((sizeBefore + 1) == departmentDao.getDepartments()
                .collect(Collectors.toList()).size());
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
        Department newDepartment = new Department(EDUCATION, DEPARTMENT_OF_EDUCATION);
        newDepartment.setDepartmentId(departmentDao.addDepartment(newDepartment));
        newDepartment.setDepartmentName(NEW_EDUCATION);
        newDepartment.setDescription(NEW_DEPARTMENT_OF_EDUCATION);
        departmentDao.updateDepartment(newDepartment);
        Department updatedDepartment = departmentDao.getDepartmentById(newDepartment.getDepartmentId()).get();
        Assert.assertTrue(newDepartment.getDepartmentId().equals(updatedDepartment.getDepartmentId()));
        Assert.assertTrue(newDepartment.getDepartmentName().equals(updatedDepartment.getDepartmentName()));
        Assert.assertTrue(newDepartment.getDescription().equals(updatedDepartment.getDescription()));
    }

    @Test
    public void deleteDepartment() {
        Department department = new Department(EDUCATION, DEPARTMENT_OF_EDUCATION);
        int deptId = departmentDao.addDepartment(department);
        List<Department> departments = departmentDao.getDepartments()
                .collect(Collectors.toList());
        int sizeBefore = departments.size();
        departmentDao.deleteDepartmentById(deptId);
        Assert.assertTrue((sizeBefore - 1) == departmentDao.getDepartments()
                .collect(Collectors.toList()).size());
    }

}