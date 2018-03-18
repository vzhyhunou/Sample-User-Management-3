package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * DepartmentConsumerRest Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-test.xml"})
public class DepartmentConsumerMockRestTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Department department = new Department("n1", "d1");
    private static final Department department3 = new Department("n3", "d3");

    @Autowired
    DepartmentConsumer departmentConsumer;

    @Value("${protocol}://${host}:${port}/${point.departments}")
    private String url;

    @Autowired
    private RestTemplate mockRestTemplate;

    @After
    public void tearDown() {
        reset(mockRestTemplate);
    }

    @Test
    public void getAllDepartments() {

        List<Department> expectedResult = new ArrayList<>(2);
        expectedResult.add(department);
        expectedResult.add(department3);
        expect(mockRestTemplate.getForEntity(url, List.class))
                .andReturn(new ResponseEntity<>(expectedResult, HttpStatus.OK));
        replay(mockRestTemplate);

        List<Department> departments = departmentConsumer.getAllDepartments();
        assertEquals(2, departments.size());
    }

    @Test
    public void getDepartmentById() {

        expect(mockRestTemplate.getForEntity(url + "/3", Department.class))
                .andReturn(new ResponseEntity<>(department3, HttpStatus.FOUND));
        replay(mockRestTemplate);

        departmentConsumer.getDepartmentById(3);
    }

}