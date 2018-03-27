package com.epam.brest.course.dto;

/**
 * POJO Department for model.
 */
public class DepartmentDTO {

    private Integer departmentId;

    private String departmentName;

    private Integer avgSalary;

    public DepartmentDTO() {
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Integer avgSalary) {
        this.avgSalary = avgSalary;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", avgSalary=" + avgSalary +
                '}';
    }
}
