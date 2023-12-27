package com.nhom7.notificationsubsystem;

import com.nhom7.entity.Notification;

import java.util.List;

public interface INotificationSubSystem {
    public List<Notification> getAllNotification();
    public Notification getNotificationById(int id);
    public boolean createNotification(Notification notification);
}
