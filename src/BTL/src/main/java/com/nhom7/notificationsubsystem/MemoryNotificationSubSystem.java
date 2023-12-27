package com.nhom7.notificationsubsystem;

import com.nhom7.entity.Notification;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryNotificationSubSystem implements INotificationSubSystem{
    private static final List<Notification> notifications = new ArrayList<>(Arrays.asList(
            new Notification(
                        1,
                            "20200673",
                            "content",
                            Timestamp.valueOf(LocalDateTime.now())
                    )
    ));
    @Override
    public List<Notification> getAllNotification() {
        return notifications;
    }

    @Override
    public Notification getNotificationById(int id) {
        for (Notification notification : notifications){
            if (notification.getId() == id){
                return  notification;
            }
        }
        return null;
    }

    @Override
    public boolean createNotification(Notification notification) {
        notifications.add(notification);
        return true;
    }
}
