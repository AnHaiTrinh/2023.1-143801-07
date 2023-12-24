package com.nhom7.dbsubsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
//    public static ResultSet query(String sql) throws SQLException {
//        return getConnection().prepareStatement(sql).executeQuery();
//    }
    /*PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM employee"
            );
            ResultSet rs = statement.executeQuery();*/
}
