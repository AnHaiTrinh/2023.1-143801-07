package com.nhom7.entity.employee.department;

public class AccountingDepartment extends Department{
    static {
        DepartmentFactory.instance().registerDepartment(new AccountingDepartment());
    }
    public AccountingDepartment() {
        this.departmentName = "Accounting";
    }
}
