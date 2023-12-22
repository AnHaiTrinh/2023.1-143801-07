package com.nhom7.hrsubsystem;

import com.nhom7.entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryHRSubSystem implements IHRSubSystem {

    private static final List<Employee> employeeList = new ArrayList<>(Arrays.asList(
            new Employee(
                    "20200673",
                    "Le Anh Vu",
                    "HR Manager",
                    "Human Resource"
            ),
            new Employee(
                    "20200260",
                    "Nguyen Kim Hung",
                    "Head of Department",
                    "Production Factory"
            ),
            new Employee(
                    "20200421",
                    "Nguyen Van Nam",
                    "Worker",
                    "Production Factory"
            ),
            new Employee(
                    "20200196",
                    "Trinh An Hai",
                    "Officer",
                    "Accounting"
            )
    ));
    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(String id) {
        for(Employee employee : employeeList){
            if(employee.getId().equals(id))
                return employee;
        }
        return null;
    }

    @Override
    public String getEmployeeNameById(String id) {
        return null;
    }

    @Override
    public String getEmployeeDepartmentByID(String id) {
        return null;
    }
}
