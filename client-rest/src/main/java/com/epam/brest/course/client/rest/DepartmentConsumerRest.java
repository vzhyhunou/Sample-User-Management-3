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
    public Department findById(final Integer id) {
        ResponseEntity<Department> responseEntity = restTemplate.getForEntity(url + "/" + id, Department.class);
        final Department department = responseEntity.getBody();
        return department;
    }

    @Override
    public Department create(Department department) {
        ResponseEntity<Department> responseEntity = restTemplate.postForEntity(url, department, Department.class);
        final Department result = responseEntity.getBody();
        return result;
    }

    @Override
    public void update(final Department department) {
        restTemplate.put(url, department);
    }

    @Override
    @Deprecated
    public void updateDepartmentDescription(Integer departmentId, String description) {
        //FIXME remove deprecated
    }

    @Override
    public Stream<Department> findAll() {
        //FIXME implement
        return null;
    }

    @Override
    public Stream<DepartmentDTO> findAllDepartmentDTOs() {
        ResponseEntity<List<DepartmentDTO>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<DepartmentDTO>>() {});
        return responseEntity.getBody().stream();
    }

    @Override
    public void delete(Integer id) {
        restTemplate.delete(url + "/" + id);
    }
}
