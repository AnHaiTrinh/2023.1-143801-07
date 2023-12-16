package com.nhom7.entity.employee.department;

public class HumanResourceDepartment extends Department{
    static {
        DepartmentFactory.instance().registerDepartment(new HumanResourceDepartment());
    }
    public HumanResourceDepartment() {
        this.departmentName = "Human Resource";
    }
}
