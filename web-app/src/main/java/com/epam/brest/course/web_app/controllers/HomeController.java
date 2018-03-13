package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home MVC controller.
 */
@Controller
public class HomeController {

    /**
     * Redirect to default page -> departments
     **/
    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        return "redirect:departments";
    }
}
