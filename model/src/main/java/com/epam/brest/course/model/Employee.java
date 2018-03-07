package com.epam.brest.course.model;

/**
 * POJO Employee for model.
 */
public class Employee {

    private Integer employeeId;

    private String employeeName;

    private Integer salary;

    private Integer departmentId;

    public Employee() {
    }

    public Employee(String employeeName, Integer salary, Integer departmentId) {
        this.employeeName = employeeName;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    /**
     * Get Employee Id.
     * @return employeeId.
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public java.lang.String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName=" + employeeName +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                '}';
    }
}
