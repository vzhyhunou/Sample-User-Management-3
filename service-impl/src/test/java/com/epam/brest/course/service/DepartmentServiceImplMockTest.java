package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.easymock.EasyMock.anyObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class DepartmentServiceImplMockTest {

    private static final int ID = 1;
    private static final String DESC = "Academic Department";
    private static final Department DEPARTMENT = new Department("Distribution", "Distribution Department");

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentDao mockDepartmentDao;

    @Test
    public void updateDepartmentDescription() {
        EasyMock.expect(mockDepartmentDao.getDepartmentById(EasyMock.anyInt())).andReturn(Optional.of(DEPARTMENT));
        EasyMock.expect(mockDepartmentDao.updateDepartment(anyObject())).andReturn(1);
        EasyMock.replay(mockDepartmentDao);

        departmentService.updateDepartmentDescription(ID, DESC);

        Assert.assertEquals(DESC, DEPARTMENT.getDescription());
    }
}