package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

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
        LOGGER.debug("findById({})", departmentId);
        return departmentDao.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Failed to get department from DB"));
    }

    @Override
    public Department addDepartment(Department department) {
        LOGGER.debug("create({})", department);
        department.setDepartmentId(departmentDao.create(department));
        return department;
    }

    @Override
    public void updateDepartment(Department department) {
        LOGGER.debug("update({})", department);
        departmentDao.update(department);
    }

    @Override
    public void updateDepartmentDescription(Integer departmentId, String description) {
        LOGGER.debug("updateDepartmentDescription({}, {})", departmentId, description);
        departmentDao.findById(departmentId)
                .map(d -> {
                    d.setDescription(description);
                    return d;
                })
                .ifPresent(departmentDao::update);
   }

    @Override
    public Stream<Department> getDepartments() {
        LOGGER.debug("findAll()");
        return departmentDao.findAll();
    }

    @Override
    public Stream<DepartmentDTO> getDepartmentDTOs() {
        LOGGER.debug("findAllDepartmentDTOs()");
        return departmentDao.findAllDepartmentDTOs();
    }

    @Override
    public void deleteDepartmentById(Integer id) {
        LOGGER.debug("delete({})", id);
        departmentDao.delete(id);
   }
}
