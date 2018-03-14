package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * Goto departments page.
     *
     * @return view name
     */
    @GetMapping(value = "/departments")
    public final String departments(Model model) {
        Collection<Department> departments = departmentService.getDepartments();
                //Collections.emptyList();
        model.addAttribute("departments", departments);
        return "departments";
    }

    /**
     * Goto department page.
     *
     * @return view name
     */
    @GetMapping(value = "/department/{id}")
    public final String getDepartmentById(@PathVariable Integer id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "department";
    }
}
