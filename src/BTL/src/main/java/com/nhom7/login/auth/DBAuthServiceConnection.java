package com.nhom7.login.auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAuthServiceConnection {
    private static Connection connection;
    private static final String DB_AUTH_SERVICE_URL = System.getenv("DB_AUTH_SERVICE_URL");

    public static Connection getConnection() throws SQLException {
        if(connection == null || !connection.isValid(15)){
            try {
                connection = DriverManager.getConnection(DB_AUTH_SERVICE_URL);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return connection;
    }
}
