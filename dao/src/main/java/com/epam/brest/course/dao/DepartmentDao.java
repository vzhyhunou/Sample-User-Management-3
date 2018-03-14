package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Department DAO Interface.
 */
public interface DepartmentDao {

    /**
     * Get departments list.
     *
     * @return departments list.
     * @throws DataAccessException on data access error.
     */
    List<Department> getDepartments();

    /**
     * Get Department By Id.
     *
     * @param departmentId id
     * @return Department
     * @throws DataAccessException on data access error.
     */
    Department getDepartmentById(Integer departmentId);

    /**
     * Persist new department.
     *
     * @param department new depatrment
     * @return department with id.
     * @throws DataAccessException on data access error.
     */
    Department addDepartment(Department department);

    /**
     * Update department.
     *
     * @param department depatrment
     * @throws DataAccessException on data access error.
     */
    void updateDepartment(Department department);

    /**
     * Delete department.
     *
     * @param id depatrment id
     * @throws DataAccessException on data access error.
     */
    void deleteDepartmentById(Integer id);

}
