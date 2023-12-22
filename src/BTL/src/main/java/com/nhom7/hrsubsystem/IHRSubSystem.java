package com.nhom7.hrsubsystem;

import com.nhom7.entity.Employee;

import java.util.List;

public interface IHRSubSystem {
    public List<Employee> getAllEmployees();

    public Employee getEmployeeById(String id);
    public String getEmployeeNameById(String id);
    public String getEmployeeDepartmentByID(String id);
}


