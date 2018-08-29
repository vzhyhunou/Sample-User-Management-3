package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;

public class DepartmentConsumerRest implements DepartmentService {

    private String url;

    private RestTemplate restTemplate;

    public DepartmentConsumerRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        ResponseEntity<Department> responseEntity = restTemplate.getForEntity(url + "/" + departmentId, Department.class);
        Department department = responseEntity.getBody();
        return department;
    }

    @Override
    public Department addDepartment(Department department) {
        ResponseEntity<Department> responseEntity = restTemplate.postForEntity(url, department, Department.class);
        Department result = responseEntity.getBody();
        return result;
    }

    @Override
    public void updateDepartment(Department department) {
        restTemplate.put(url, department);
    }

    @Override
    @Deprecated
    public void updateDepartmentDescription(Integer departmentId, String description) {

    }

    @Override
    public Stream<Department> getDepartments() {
        return null;
    }

    @Override
    public Stream<DepartmentDTO> getDepartmentDTOs() {
        ResponseEntity<List<DepartmentDTO>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<DepartmentDTO>>() {});
        return responseEntity.getBody().stream();
    }

    @Override
    public void deleteDepartmentById(Integer id) {
        restTemplate.delete(url + "/" + id);
    }
}
