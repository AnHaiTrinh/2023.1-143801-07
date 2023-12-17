package com.nhom7.login;

import com.nhom7.entity.employee.Employee;

public class EmployeeContext {
    private static Employee employee;

    private EmployeeContext() {
    }

    public static void create(Employee employee) {
        EmployeeContext.employee = employee;
    }

    public static String getId() {
        return employee.getId();
    }

    public static String getName() {
        return employee.getName();
    }

    public static String getTitle() {
        return employee.getTitle();
    }

    public static String getDepartment() {
        return employee.getDepartment();
    }

    public static void destroy() {
        employee = null;
    }
}
