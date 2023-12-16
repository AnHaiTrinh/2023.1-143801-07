package com.nhom7.entity.employee.department;

public class ProductionFactoryDepartment extends Department{
    static {
        DepartmentFactory.instance().registerDepartment(new ProductionFactoryDepartment());
    }
    public ProductionFactoryDepartment() {
        this.departmentName = "Production Factory";
    }
}
