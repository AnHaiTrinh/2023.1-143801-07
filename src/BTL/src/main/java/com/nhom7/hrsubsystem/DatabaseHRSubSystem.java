package com.nhom7.hrsubsystem;

import com.nhom7.entity.Employee;

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
                        rs.getString("title"),
                        rs.getString("department")
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
                        rs.getString("title"),
                        rs.getString("department")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public String getEmployeeNameById(String id) {
        String name = null;
        try {
            Connection connection = DatabaseHRSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT name FROM employee WHERE id = ?"
            );
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public String getEmployeeDepartmentByID(String id) {
        String department = null;
        try {
            Connection connection = DatabaseHRSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT department FROM employee WHERE id = ?"
            );
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                department = rs.getString("department");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
}
