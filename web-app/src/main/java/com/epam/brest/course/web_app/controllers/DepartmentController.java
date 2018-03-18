package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private DepartmentService departmentService;

    /**
     * Goto departments list page.
     *
     * @return view name
     */
    @GetMapping(value = "/departments")
    public final String departments(Model model) {
        LOGGER.debug("getDepartments({})", model);
        Collection<DepartmentDTO> departments = departmentService.getDepartmentDTOs();
        model.addAttribute("departments", departments);
        return "departments";
    }

    /**
     * Goto new department page.
     *
     * @return view name
     */
    @GetMapping(value = "/department")
    public final String gotoAddDepartmentPage(Model model) {
        LOGGER.debug("addDepartment({})", model);
        Department department = new Department();
        model.addAttribute("isNew", true);
        model.addAttribute("department", department);
        return "department";
    }

    /**
     *  Persist new department into persistence storage.
     *
     * @param department new department with filled data.
     * @param result data binding result
     * @return view name
     */
    @PostMapping(value = "/department")
    public String addDepartment(@Valid Department department,
                                BindingResult result
    ) {
        LOGGER.debug("addDepartment({}, {})", department, result);
        if (result.hasErrors()) {
            return "department";
        } else {
            this.departmentService.addDepartment(department);
            return "redirect:/departments";
        }
    }

    /**
     * Goto edit department page.
     *
     * @return view name
     */
    @GetMapping(value = "/department/{id}")
    public final String gotoEditDepartmentPage(@PathVariable Integer id, Model model) {
        LOGGER.debug("gotoEditDepartmentPage({},{})", id, model);
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("isNew", false);
        model.addAttribute("department", department);
        return "department";
    }

    /**
     * Update department into persistence storage.
     *
     * @return view name
     */
    @PostMapping(value = "/department/{id}")
    public String updateDepartment(@Valid Department department,
                                   BindingResult result
    ) {
        LOGGER.debug("updateDepartment({}, {})", department, result);
        if (result.hasErrors()) {
            return "department";
        } else {
            this.departmentService.updateDepartment(department);
            return "redirect:/departments";
        }
    }

    /**
     * Delete department.
     *
     * @return view name
     */
    @GetMapping(value = "/department/{id}/delete")
    public final String deleteDepartmentById(@PathVariable Integer id, Model model) {
        LOGGER.debug("deleteDepartmentById({},{})", id, model);
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }
}
