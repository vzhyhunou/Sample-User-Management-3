package com.epam.brest.course.client;

import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.Scanner;

/**
 * REST client console application demo.
 */
public class DemoApp {

    private DepartmentService departmentService;

    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        DepartmentService departmentService = ctx.getBean(DepartmentService.class);

        DemoApp demoApp = new DemoApp(departmentService);
        demoApp.menu();
    }

    public DemoApp(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    private void menu() {

        int swValue = 0;

        System.out.println("=================================");
        System.out.println("|   MENU SELECTION DEMO         |");
        System.out.println("=================================");
        System.out.println("| Options:                      |");
        System.out.println("|        1. Get all departments |");
        System.out.println("|        2. Get department by id|");
        System.out.println("|        3. Add department      |");
        System.out.println("|        4. Exit                |");
        System.out.println("=================================");
        while (swValue != 4) {
            System.out.print("Select option: ");
            if (sc.hasNextInt()) {
                swValue = sc.nextInt();
                checkValue(swValue);
            } else {
                System.out.println("Bad value: " + sc.next());
            }
        }
    }

    private void checkValue(int item) {
        switch (item) {
            case 1:
                getAllDepartments();
                break;
            case 2:
                getDepartmentById();
                break;
            case 3:
                addDepartment();
                break;
            case 4:
                System.out.println("Exit.");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    private void getAllDepartments() {
        Collection<DepartmentDTO> departments = departmentService.getDepartmentDTOs();
        System.out.println("departments: " + departments);
    }

    private void getDepartmentById() {
        int id = 0;
        System.out.print("    Enter department id: ");
        if (sc.hasNextLine()) {
            id = sc.nextInt();
        }

        try {
            Department department = departmentService.getDepartmentById(id);
            System.out.println("    Department: " + department);
        } catch (ServerDataAccessException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

    private void addDepartment() {
        String name = "";
        System.out.print("    Enter department name: ");
        if (sc.hasNextLine()) {
            name = sc.next();
        }
        String desc = "";
        System.out.print("    Enter department desc: ");
        if (sc.hasNextLine()) {
            desc = sc.next();
        }

        try {
            Department department = departmentService.addDepartment(new Department(name, desc));
            System.out.println("    Department: " + department);
        } catch (ServerDataAccessException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }
}
