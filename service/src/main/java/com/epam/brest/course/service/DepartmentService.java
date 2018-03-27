package com.epam.brest.course.service;

import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;

import java.util.Collection;

/**
 * Department Service Interface.
 */
public interface DepartmentService {

    /**
     * Get Department By Id.
     *
     * @param departmentId id
     * @return Department
     */
    Department getDepartmentById(Integer departmentId);

    /**
     * Persist new department.
     *
     * @param department new department
     * @return department with id.
     */
    Department addDepartment(Department department);

    /**
     * Update department.
     *
     * @param department department
     */
    void updateDepartment(Department department);

    /**
     * Update department description.
     *
     * @param departmentId id
     * @param description new description
     */
    @Deprecated
    void updateDepartmentDescription(Integer departmentId, String description);

    /**
     * Get departments list.
     *
     * @return departments list.
     */
    Collection<Department> getDepartments();

    /**
     * Get department DTOs list.
     *
     * @return department DTOs list.
     */
    Collection<DepartmentDTO> getDepartmentDTOs();

    /**
     * Delete department.
     *
     * @param id department id
     */
    void deleteDepartmentById(Integer id);
}
