package com.nhom7.notificationsubsystem;

import com.nhom7.dbsubsystem.DBSubsystemConnection;
import com.nhom7.entity.Notification;
import com.nhom7.hrsubsystem.DatabaseHRSubsystemConnection;

import java.sql.*;
import java.util.List;

public class RemoteNotificationDBSubSystem implements  INotificationSubSystem{
    @Override
    public List<Notification> getAllNotification() {
        return null;
    }

    @Override
    public Notification getNotificationById(int id) {
        return null;
    }

    @Override
    public boolean createNotification(Notification notification) {
        try {
            Connection connection = DatabaseHRSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO notification " +
                            "(employee_id, content, time) " +
                            "VALUES (?, ?, ?)"
            );
            statement.setString(1, notification.getSendFromEmployeeId());
            statement.setString(2, notification.getNotificationContent());
            statement.setTimestamp(3, notification.getSendAt());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
