package com.nhom7.login.auth;

import com.nhom7.entity.employee.Employee;

public interface IAuthService {
    public Employee authenticate(String employeeId, String password);
}
