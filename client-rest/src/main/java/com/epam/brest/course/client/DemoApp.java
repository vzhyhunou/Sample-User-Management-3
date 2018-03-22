package com.epam.brest.course.client;

import com.epam.brest.course.client.rest.DepartmentConsumer;
import com.epam.brest.course.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * REST client console application demo.
 */
@Component
public class DemoApp {

    @Autowired
    DepartmentConsumer departmentConsumer;

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");

        DemoApp demoApp = ctx.getBean(DemoApp.class);
        demoApp.menu();
    }

    private void menu() {

        int swValue = 0;

        System.out.println("=================================");
        System.out.println("|   MENU SELECTION DEMO         |");
        System.out.println("=================================");
        System.out.println("| Options:                      |");
        System.out.println("|        1. Get all departments |");
        System.out.println("|        2. Get department by id|");
        System.out.println("|        3. Exit                |");
        System.out.println("=================================");
        while (swValue != 3) {
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
                System.out.println("Exit.");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    private void getAllDepartments() {
        List<Department> departments = departmentConsumer.getAllDepartments();
        System.out.println("departments: " + departments);
    }

    private void getDepartmentById() {
        String id = "";
        System.out.print("    Enter department id: ");
        if (sc.hasNextLine()) {
            id = sc.next();
        }

        try {
            Department department = departmentConsumer.getDepartmentById(Integer.valueOf(id));
            System.out.println("    Department: " + department);
        } catch (ServerDataAccessException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }
}
