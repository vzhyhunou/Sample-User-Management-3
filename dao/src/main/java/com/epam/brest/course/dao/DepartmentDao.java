package com.epam.brest.course.dao;

import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Department DAO Interface.
 */
public interface DepartmentDao {

    /**
     * Get departments list.
     *
     * @return departments list.
     */
    Stream<Department> getDepartments();

    /**
     * Get department DTOs list.
     *
     * @return department DTOs list.
     */
    Stream<DepartmentDTO> getDepartmentDTOs();

    /**
     * Get Department By Id.
     *
     * @param departmentId id
     * @return Department
     */
    Optional<Department> getDepartmentById(Integer departmentId);

    /**
     * Persist new department.
     *
     * @param department new department
     * @return department with id.
     */
    int addDepartment(Department department);

    /**
     * Update department.
     *
     * @param department department
     */
    int updateDepartment(Department department);

    /**
     * Delete department.
     *
     * @param id department id
     */
    int deleteDepartmentById(Integer id);

}
