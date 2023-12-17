package com.nhom7.hrsubsystem;

import com.nhom7.entity.employee.Employee;
import com.nhom7.entity.employee.department.DepartmentFactory;
import com.nhom7.entity.employee.title.TitleFactory;

import java.util.ArrayList;
import java.util.List;

public class MemoryHRSubSystem implements IHRSubSystem {

    private static List<Employee> employeeList = List.of(new Employee[]{
            new Employee(
                    "20200673",
                    "Le Anh Vu",
                    TitleFactory.instance().createTitle("HR Manager"),
                    DepartmentFactory.instance().createDepartment("Human Resource")
            ),
            new Employee(
                    "20200260",
                    "Nguyen Kim Hung",
                    TitleFactory.instance().createTitle("Head of Department"),
                    DepartmentFactory.instance().createDepartment("Production Factory")
            ),
            new Employee(
                    "20200421",
                    "Nguyen Van Nam",
                    TitleFactory.instance().createTitle("Worker"),
                    DepartmentFactory.instance().createDepartment("Production Factory")
            ),
            new Employee(
                    "20200196",
                    "Trinh An Hai",
                    TitleFactory.instance().createTitle("Officer"),
                    DepartmentFactory.instance().createDepartment("Accounting")
            ),
    });
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
}
