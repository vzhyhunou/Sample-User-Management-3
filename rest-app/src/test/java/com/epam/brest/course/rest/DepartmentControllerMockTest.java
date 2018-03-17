package com.epam.brest.course.rest;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class DepartmentControllerMockTest {

    @Autowired
    private DepartmentRestController departmentRestController;

    private MockMvc mockMvc;

    @Autowired
    private DepartmentService departmentService;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(departmentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(departmentService);
        reset(departmentService);
    }

    @Test
    public void getDepartments() throws Exception {
        expect(departmentService.getDepartments()).andReturn(Collections.singletonList(new Department("n", "d")));
        replay(departmentService);

        mockMvc.perform(
                get("/departments")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }
}
