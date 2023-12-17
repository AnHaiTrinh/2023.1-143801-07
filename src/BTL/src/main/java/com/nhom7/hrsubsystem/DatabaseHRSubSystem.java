package com.nhom7.hrsubsystem;

import com.nhom7.entity.employee.Employee;
import com.nhom7.entity.employee.department.DepartmentFactory;
import com.nhom7.entity.employee.title.TitleFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseHRSubSystem implements IHRSubSystem {
    @Override
    public List<Employee> getAllEmployees() {
        try {
            ArrayList<Employee> employeeList = new ArrayList<>();
            Connection connection = DatabaseHRSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM employee"
            );
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Employee employee = new Employee(
                        rs.getString("id"),
                        rs.getString("name"),
                        TitleFactory.instance().createTitle(rs.getString("title")),
                        DepartmentFactory.instance().createDepartment(rs.getString("department"))
                );
                employeeList.add(employee);
            }
            return employeeList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(String id) {
        Employee employee = null;
        try {
            Connection connection = DatabaseHRSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM employee WHERE id = ?"
            );
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                employee = new Employee(
                        rs.getString("id"),
                        rs.getString("name"),
                        TitleFactory.instance().createTitle(rs.getString("title")),
                        DepartmentFactory.instance().createDepartment(rs.getString("department"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
