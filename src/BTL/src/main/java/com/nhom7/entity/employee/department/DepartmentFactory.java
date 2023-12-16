package com.nhom7.entity.employee.department;

import java.util.HashMap;
import java.util.Map;

public class DepartmentFactory {
    private static final DepartmentFactory instance = new DepartmentFactory();
    private final Map<String, Department> registeredDepartment = new HashMap<>();

    private DepartmentFactory() {
    }

    public static DepartmentFactory instance() {
        return instance;
    }

    public void registerDepartment(Department department) {
        registeredDepartment.put(department.getDepartment(), department);
    }

    public Department createDepartment(String department) {
        return registeredDepartment.get(department);
    }
}
