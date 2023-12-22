package com.nhom7.hrsubsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHRSubsystemConnection {
    private static Connection connection;

    //private static final String HR_SUBSYSTEM_URL = "jdbc:postgresql://localhost:5430/postgres?user=postgres&password=postgres";
    private static final String HR_SUBSYSTEM_URL = System.getenv("HR_SUBSYSTEM_URL");
    public static Connection getConnection() throws SQLException {
        if(connection == null || !connection.isValid(15)){
            try {
                connection = DriverManager.getConnection(HR_SUBSYSTEM_URL);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return connection;
    }
}
