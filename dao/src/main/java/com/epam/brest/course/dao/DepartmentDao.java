package com.epam.brest.course.dao;

import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;

import java.util.List;

/**
 * Department DAO Interface.
 */
public interface DepartmentDao {

    /**
     * Get departments list.
     *
     * @return departments list.
     */
    List<Department> getDepartments();

    /**
     * Get department DTOs list.
     *
     * @return department DTOs list.
     */
    List<DepartmentDTO> getDepartmentDTOs();

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
     * Delete department.
     *
     * @param id department id
     */
    void deleteDepartmentById(Integer id);

}
