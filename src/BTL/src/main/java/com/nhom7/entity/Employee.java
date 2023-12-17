package com.nhom7.entity;

public class Employee {
    private final String id;
    private final String name;

    private final String title;
    private final String department;

    public Employee(String id, String name, String title, String department) {
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
        return title;
    }

    public String getDepartment() {
        return department;
    }
}
