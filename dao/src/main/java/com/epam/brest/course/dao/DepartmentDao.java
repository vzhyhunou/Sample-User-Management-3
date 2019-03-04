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
     * Get departments.
     *
     * @return departments stream.
     */
    Stream<Department> findAll();

    /**
     * Get department DTOs.
     *
     * @return department DTOs stream.
     */
    Stream<DepartmentDTO> findAllDepartmentDTOs();

    /**
     * Get Department By Id.
     *
     * @param id id
     * @return Department
     */
    Optional<Department> findById(final Integer id);

    /**
     * Persist new department.
     *
     * @param department new department
     * @return department id.
     */
    int create(final Department department);

    /**
     * Update department.
     *
     * @param department department
     */
    void update(final Department department);

    /**
     * Delete department with specified id.
     *
     * @param id department id
     */
    void delete(final Integer id);

}
