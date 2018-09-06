package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.easymock.EasyMock.anyObject;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
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
        mockDepartmentDao.updateDepartment(anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockDepartmentDao);

        departmentService.updateDepartmentDescription(ID, DESC);

        assertEquals(DESC, DEPARTMENT.getDescription());
    }
}