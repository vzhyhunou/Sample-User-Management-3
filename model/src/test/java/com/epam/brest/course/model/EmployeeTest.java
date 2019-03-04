package com.epam.brest.course.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EmployeeTest {

    public static final String VASIA = "Vasia";

    @Test
    public void getEmployeeName() {

        Employee employee = new Employee();
        employee.setEmployeeName(VASIA);
        assertTrue(employee.getEmployeeName().equals(VASIA));
        assertEquals(VASIA, employee.getEmployeeName());
    }
}