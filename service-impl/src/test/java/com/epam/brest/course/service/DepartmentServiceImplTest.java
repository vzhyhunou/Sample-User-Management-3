package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:service-test.xml", "classpath:test-db-spring.xml", "classpath:dao.xml"})
public class DepartmentServiceImplTest {

    private static final int ID = 1;
    private static final String DESC = "Academic Department";

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void updateDepartmentDescription() {

        departmentService.updateDepartmentDescription(ID, DESC);

        Department department = departmentService.getDepartmentById(ID);
        assertEquals(DESC, department.getDescription());
    }
}