package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    @GetMapping(value = "/departments")
    public String departments(Model model) {
        return "departments";
    }

    @GetMapping(value = "/department")
    public String department(Model model) {
        return "department";
    }
}
