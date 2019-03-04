package com.epam.brest.course.dao;

import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String DEPARTMENT_ID = "departmentId";
    public static final String DEPARTMENT_NAME = "departmentName";
    public static final String DESCRIPTION = "description";
    public static final String AVG_SALARY = "avgSalary";

    @Value("${department.select}")
    private String selectSql;

    @Value("${department.selectAvgSalary}")
    private String selectAvgSalarySql;

    @Value("${department.selectById}")
    private String selectByIdSql;

    @Value("${department.checkDepartment}")
    private String checkDepartmentSql;

    @Value("${department.insert}")
    private String insertSql;

    @Value("${department.update}")
    private String updateSql;

    @Value("${department.delete}")
    private String deleteSql;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Department> findAll() {
        LOGGER.debug("findAll()");
        List<Department> departments =
                namedParameterJdbcTemplate.query(selectSql, new DepartmentRowMapper());
        return departments.stream();
    }

    @Override
    public Stream<DepartmentDTO> findAllDepartmentDTOs() {
        LOGGER.debug("findAllDepartmentDTOs()");
        List<DepartmentDTO> list =
                namedParameterJdbcTemplate.query(selectAvgSalarySql, new DepartmentDTORowMapper());
        return list.stream();
    }

    @Override
    public Optional<Department> findById(final Integer id) {
        LOGGER.debug("findById({})", id);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, id);
        Department department = namedParameterJdbcTemplate.queryForObject(selectByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Department.class));
        return Optional.ofNullable(department);
    }

    @Override
    public int create(final Department department) {
        LOGGER.debug("create({})", department);
        return Optional.of(department)
                .filter(this::isNameUnique)
                .map(this::insertDepartment)
                .orElseThrow(() -> new IllegalArgumentException("Department with the same name already exists in DB."));
    }

    private boolean isNameUnique(Department department) {
        return namedParameterJdbcTemplate.queryForObject(checkDepartmentSql,
                new MapSqlParameterSource("departmentName", department.getDepartmentName()),
                Integer.class) == 0;
    }

    private int insertDepartment(Department department) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("departmentName", department.getDepartmentName());
        namedParameters.addValue("description", department.getDescription());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        return Optional.of(namedParameterJdbcTemplate.update(insertSql, namedParameters, generatedKeyHolder))
                .filter(this::successfullyUpdated)
                .map(n -> generatedKeyHolder.getKey().intValue())
                .orElseThrow(() -> new RuntimeException("Failed to add a department to DB"));
    }

    @Override
    public void update(final Department department) {
        Optional.of(namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(department)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update department in DB"));
    }

    @Override
    public void delete(final Integer id) {
        Optional.of(namedParameterJdbcTemplate.getJdbcOperations().update(deleteSql, id))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete department from DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            department.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            department.setDescription(resultSet.getString(DESCRIPTION));
            return department;
        }
    }

    private class DepartmentDTORowMapper implements RowMapper<DepartmentDTO> {

        @Override
        public DepartmentDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            DepartmentDTO dto = new DepartmentDTO();
            dto.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            dto.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            dto.setAvgSalary(resultSet.getInt(AVG_SALARY));
            return dto;
        }
    }

}
