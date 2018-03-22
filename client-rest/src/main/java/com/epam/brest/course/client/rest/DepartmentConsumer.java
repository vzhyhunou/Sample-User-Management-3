package com.epam.brest.course.client.rest;

import com.epam.brest.course.client.ServerDataAccessException;
import com.epam.brest.course.model.Department;

import java.util.List;

/**
 * Department Consumer Api.
 */
public interface DepartmentConsumer {

    /**
     * Get all departments list.
     *
     * @return all departments list
     */
    List<Department> getAllDepartments() throws ServerDataAccessException;

    /**
     * Get department by Id.
     *
     * @param departmentId department identifier.
     * @return department.
     */
    Department getDepartmentById(Integer departmentId) throws ServerDataAccessException;

    /**
     * Create new department.
     *
     * @param department department.
     * @return new department.
     */
    Department addDepartment(Department department) throws ServerDataAccessException;
}
