package com.nhom7.login.auth;

import com.nhom7.entity.Employee;

public class MockAuthService implements IAuthService{
    @Override
    public Employee authenticate(String employeeId, String password) {
        return new Employee(
                "20200673",
                "Le Anh Vu",
                "HR Manager",
                "Human Resource"
        );
    }
}
