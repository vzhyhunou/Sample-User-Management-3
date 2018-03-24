package com.epam.brest.course.rest;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class DepartmentRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private DepartmentService departmentService;

    //curl -v localhost:8080/departments
    @GetMapping(value = "/departments")
    Collection<Department> departments() {
        LOGGER.debug("departments()");
        return departmentService.getDepartments();
    }

    //curl -v localhost:8080/departments/1
    @GetMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    Department departmentById(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("departmentById({})", id);
        return departmentService.getDepartmentById(id);
    }

    //curl -H "Content-Type: application/json" -X POST -d '{"departmentName":"xyz","description":"xyz"}' -v localhost:8080/departments
    @PostMapping(value = "/departments")
    @ResponseStatus(HttpStatus.CREATED)
    Department addDepartment(@RequestBody Department department) {
        LOGGER.debug("addDepartment({})", department);
        return departmentService.addDepartment(department);
    }

    @DeleteMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    void deleteDepartment(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("deleteDepartment({})", id);
        departmentService.deleteDepartmentById(id);
    }
}
