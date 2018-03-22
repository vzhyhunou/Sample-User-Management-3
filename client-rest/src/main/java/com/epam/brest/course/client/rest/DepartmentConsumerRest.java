package com.epam.brest.course.client.rest;

import com.epam.brest.course.client.ServerDataAccessException;
import com.epam.brest.course.model.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Department Consumer REST implementation.
 */
public class DepartmentConsumerRest implements DepartmentConsumer {

    private String url;

    private RestTemplate restTemplate;

    public DepartmentConsumerRest(String url) {
        this.url = url;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Department> getAllDepartments() throws ServerDataAccessException {
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        List<Department> departments = (List<Department>) responseEntity.getBody();
        return departments;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) throws ServerDataAccessException {
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/" + departmentId, Department.class);
        Object department = responseEntity.getBody();
        return (Department) department;
    }

    @Override
    public Department addDepartment(Department department) throws ServerDataAccessException {
        ResponseEntity responseEntity = restTemplate.postForEntity(url, department, Department.class);
        Object result = responseEntity.getBody();
        return (Department) result;
    }
}
