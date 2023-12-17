package com.nhom7.dbsubsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSubsystemConnection {
    private static Connection connection;
    private static final String DB_SUBSYSTEM_URL = System.getenv("DB_SUBSYSTEM_URL");

    public static Connection getConnection() throws SQLException {
        if(connection == null || !connection.isValid(15)){
            try {
                connection = DriverManager.getConnection(DB_SUBSYSTEM_URL);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return connection;
    }
}
