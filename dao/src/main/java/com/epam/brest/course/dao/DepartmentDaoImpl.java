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

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Department> getDepartments() {
        LOGGER.debug("getDepartments()");
        List<Department> departments =
                namedParameterJdbcTemplate.getJdbcOperations().query(selectSql, new DepartmentRowMapper());
        return departments;
    }

    @Override
    public List<DepartmentDTO> getDepartmentDTOs() {
        LOGGER.debug("getDepartmentDTOs()");
        List<DepartmentDTO> list =
                namedParameterJdbcTemplate.getJdbcOperations().query(selectAvgSalarySql, new DepartmentDTORowMapper());
        return list;
    }

//    @Override
//    public Department getDepartmentById(Integer departmentId) {
//        SqlParameterSource namedParameters =
//                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
//        Department department =
//                namedParameterJdbcTemplate.queryForObject(selectByIdSql, namedParameters,
//                        new DepartmentRowMapper());
//        return department;
//    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department = namedParameterJdbcTemplate.queryForObject(selectByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Department.class));
        return department;
    }

    @Override
    public Department addDepartment(Department department) {
        LOGGER.debug("addDepartment({})", department);
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentName", department.getDepartmentName());
        Integer result =
                namedParameterJdbcTemplate.queryForObject(checkDepartmentSql, namedParameters, Integer.class);

        LOGGER.debug("result({})", result);
        if (result == 0) {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("departmentName", department.getDepartmentName());
            namedParameters.addValue("description", department.getDescription());

            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(insertSql, namedParameters, generatedKeyHolder);
            department.setDepartmentId(generatedKeyHolder.getKey().intValue());
        } else {
            throw new IllegalArgumentException("Department with the same name already exists in DB.");
        }

        return department;
    }

    @Override
    public void updateDepartment(Department department) {
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(updateSql, namedParameter);
    }

    @Override
    public void deleteDepartmentById(Integer departmentId) {
        namedParameterJdbcTemplate.getJdbcOperations().update(deleteSql, departmentId);
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
