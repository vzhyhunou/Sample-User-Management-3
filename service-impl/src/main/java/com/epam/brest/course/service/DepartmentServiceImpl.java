package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LogManager.getLogger();

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        return departmentDao.getDepartmentById(departmentId)
                .orElseThrow(() -> new RuntimeException("Failed to get department from DB"));
    }

    @Override
    public Department addDepartment(Department department) {
        LOGGER.debug("addDepartment({})", department);
        department.setDepartmentId(departmentDao.addDepartment(department));
        return department;
    }

    @Override
    public void updateDepartment(Department department) {
        LOGGER.debug("updateDepartment({})", department);
        Optional.of(departmentDao.updateDepartment(department))
                .filter(r -> r > 0)
                .orElseThrow(() -> new RuntimeException("Failed to update department in DB"));
    }

    @Override
    public void updateDepartmentDescription(Integer departmentId, String description) {
        LOGGER.debug("updateDepartmentDescription({}, {})", departmentId, description);
        departmentDao.getDepartmentById(departmentId)
                .map(d -> {
                    d.setDescription(description);
                    return departmentDao.updateDepartment(d);
                })
                .orElseThrow(() -> new RuntimeException("Failed to update department description"));
    }

    @Override
    public Stream<Department> getDepartments() {
        LOGGER.debug("getDepartments()");
        return departmentDao.getDepartments();
    }

    @Override
    public Stream<DepartmentDTO> getDepartmentDTOs() {
        LOGGER.debug("getDepartmentDTOs()");
        return departmentDao.getDepartmentDTOs();
    }

    @Override
    public void deleteDepartmentById(Integer id) {
        LOGGER.debug("deleteDepartmentById({})", id);
        Optional.of(departmentDao.deleteDepartmentById(id))
                .filter(r -> r > 0)
                .orElseThrow(() -> new RuntimeException("Failed to delete department from DB"));
    }
}
