package com.epam.brest.course.rest;

import com.epam.brest.course.dto.DepartmentDTO;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class DepartmentControllerMockTest {

    private static DepartmentDTO departmentDTO1;
    private static DepartmentDTO departmentDTO2;

    @Autowired
    private DepartmentRestController departmentRestController;

    private MockMvc mockMvc;

    @Autowired
    private DepartmentService departmentService;

    @Before
    public void setUp() {
        departmentDTO1 = new DepartmentDTO();
        departmentDTO1.setDepartmentId(1);
        departmentDTO1.setDepartmentName("name1");
        departmentDTO2 = new DepartmentDTO();
        departmentDTO2.setDepartmentId(2);
        departmentDTO2.setDepartmentName("name2");
        mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController)
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
        expect(departmentService.findAllDepartmentDTOs())
                .andReturn(Arrays.asList(departmentDTO1, departmentDTO2).stream());
        replay(departmentService);

        mockMvc.perform(
                get("/departments")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].departmentId", is(1)))
                .andExpect(jsonPath("$[0].departmentName", is("name1")))
                .andExpect(jsonPath("$[1].departmentId", is(2)))
                .andExpect(jsonPath("$[1].departmentName", is("name2")));
    }

}
