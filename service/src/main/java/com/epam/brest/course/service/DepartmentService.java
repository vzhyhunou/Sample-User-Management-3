package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Department Service Interface.
 */
@Service
public interface DepartmentService {

    /**
     * Get Department By Id.
     *
     * @param departmentId id
     * @return Department
     * @throws DataAccessException on data access error.
     */
    Department getDepartmentById(Integer departmentId);

    /**
     * Update department description.
     *
     * @param departmentId id
     * @param description new description
     * @throws DataAccessException on data access error.
     */
    void updateDepartmentDescription(Integer departmentId, String description);

    /**
     * Get departments list.
     *
     * @return departments list.
     * @throws DataAccessException on data access error.
     */
    Collection<Department> getDepartments();

}
