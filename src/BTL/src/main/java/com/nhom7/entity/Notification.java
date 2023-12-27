package com.nhom7.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Notification {
    private int id;
    private String sendFromEmployeeId;
    private String notificationContent;
    private Timestamp sendAt;
    public Notification(int id, String sendFromEmployeeId, String notificationContent, Timestamp sendAt){
        this.id = id;
        this.sendFromEmployeeId = sendFromEmployeeId;
        this.notificationContent = notificationContent;
        this.sendAt = sendAt;
    };

    public int getId() {
        return id;
    }
    public String getSendFromEmployeeId(){
        return sendFromEmployeeId;
    }
    public String getNotificationContent(){
        return notificationContent;
    }
    public Timestamp getSendAt(){
        return sendAt;
    }
}
