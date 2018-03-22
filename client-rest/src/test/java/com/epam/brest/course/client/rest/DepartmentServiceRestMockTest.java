package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.junit.After;
import org.junit.Before;
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
import java.util.Collection;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * DepartmentConsumerRest Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-test.xml"})
public class DepartmentServiceRestMockTest {

    private static DepartmentDTO departmentDTO1;
    private static DepartmentDTO departmentDTO2;
    private static Department department;

    @Autowired
    DepartmentService departmentService;

    @Value("${protocol}://${host}:${port}/${point.departments}")
    private String url;

    @Autowired
    private RestTemplate mockRestTemplate;

    @Before
    public void init() {
        departmentDTO1 = new DepartmentDTO();
        departmentDTO1.setDepartmentId(1);
        departmentDTO1.setDepartmentName("name1");
        departmentDTO2 = new DepartmentDTO();
        departmentDTO2.setDepartmentId(2);
        departmentDTO2.setDepartmentName("name2");
        department = new Department("name", "desc");
        department.setDepartmentId(3);
    }

    @After
    public void tearDown() {
        reset(mockRestTemplate);
    }

    @Test
    public void getAllDepartments() {

        List<DepartmentDTO> expectedResult = new ArrayList<>(2);
        expectedResult.add(departmentDTO1);
        expectedResult.add(departmentDTO2);
        expect(mockRestTemplate.getForEntity(url, List.class))
                .andReturn(new ResponseEntity<>(expectedResult, HttpStatus.OK));
        replay(mockRestTemplate);

        Collection<DepartmentDTO> departments = departmentService.getDepartmentDTOs();
        assertEquals(2, departments.size());
    }

    @Test
    public void getDepartmentById() {

        expect(mockRestTemplate.getForEntity(url + "/3", Department.class))
                .andReturn(new ResponseEntity<>(department, HttpStatus.FOUND));
        replay(mockRestTemplate);

        Department result = departmentService.getDepartmentById(3);
        assertEquals(3, result.getDepartmentId().intValue());
    }

}