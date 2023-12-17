package com.nhom7.hrsubsystem;

import com.nhom7.entity.employee.Employee;

import java.util.List;

public interface IHRSubSystem {
    public List<Employee> getAllEmployees();

    public Employee getEmployeeById(String id);
}


