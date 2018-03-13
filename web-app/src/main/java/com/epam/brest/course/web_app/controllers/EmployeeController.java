package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

    @GetMapping(value = "/employees")
    public String employees(Model model) {
        return "employees";
    }

    @GetMapping(value = "/employee")
    public String employee(Model model) {
        return "employee";
    }
}
