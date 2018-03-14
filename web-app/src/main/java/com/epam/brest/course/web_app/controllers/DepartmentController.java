package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    /**
     * Goto departments page.
     *
     * @return view name
     */
    @GetMapping(value = "/departments")
    public final String departments() {
        return "departments";
    }

    /**
     * Goto department page.
     *
     * @return view name
     */
    @GetMapping(value = "/department")
    public final String department() {
        return "department";
    }
}
