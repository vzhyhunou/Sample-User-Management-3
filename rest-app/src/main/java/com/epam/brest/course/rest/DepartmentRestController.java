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
    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public @ResponseBody
    Collection<Department> departments() {
        LOGGER.debug("departments()");
        return departmentService.getDepartments();
    }

    //curl -v localhost:8080/departments/1
    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public @ResponseBody
    Department departmentsById(@PathVariable(value = "id") int id) {
        LOGGER.debug("getUser: login = {}", id);
        return departmentService.getDepartmentById(id);
    }

    //curl -H "Content-Type: application/json" -X POST -d '{"departmentName":"xyz","description":"xyz"}' -v localhost:8080/departments
    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Department addDepartment(@RequestBody Department department) {
        LOGGER.debug("addDepartment: department = {}", department);
        return departmentService.addDepartment(department);
    }
}
