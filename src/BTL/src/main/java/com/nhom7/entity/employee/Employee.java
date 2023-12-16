package com.nhom7.entity.employee;

import com.nhom7.entity.employee.title.Title;
import com.nhom7.entity.employee.department.Department;

public class Employee {
    private final String id;
    private final String name;

    private final Title title;
    private final Department department;

    public Employee(String id, String name, Title title, Department department) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title.getTitle();
    }

    public String getDepartment() {
        return department.getDepartment();
    }
}
