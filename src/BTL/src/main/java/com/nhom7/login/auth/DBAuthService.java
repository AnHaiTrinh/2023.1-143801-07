package com.nhom7.login.auth;

import com.nhom7.entity.employee.Employee;
import com.nhom7.entity.employee.department.DepartmentFactory;
import com.nhom7.entity.employee.title.TitleFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAuthService implements IAuthService{

    @Override
    public Employee authenticate(String employeeId, String password) {
        Employee employee = null;
        try{
            Connection connection = DBAuthServiceConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM employee WHERE id = ? AND password = ?"
            );
            statement.setString(1, employeeId);
            statement.setString(2, password);
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
