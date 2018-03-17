package com.epam.brest.course.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class VersionControllerMockTest {

    @Autowired
    private VersionController versionController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(versionController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void getVersionTest() throws Exception {
        mockMvc.perform(
                get("/version")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("\"1.0\""));
    }

}
