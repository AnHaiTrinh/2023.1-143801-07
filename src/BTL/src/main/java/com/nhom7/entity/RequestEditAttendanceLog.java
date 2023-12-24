package com.nhom7.entity;
import java.time.LocalDate;
import java.time.LocalTime;
public class RequestEditAttendanceLog {
    private int id;
    private final String employeeId;
    private final LocalDate day;
    private LocalTime time;
    private String requestEditType;
    private String reason;
    private String note;

    private final String attendanceMachineId;

    public RequestEditAttendanceLog(
            int id,
            String employeeId,
            LocalDate day,
            LocalTime time,
            String requestEditType,
            String reason,
            String note,
            String attendanceMachineId
    ) {
        this.id = id;
        this.employeeId = employeeId;
        this.day = day;
        this.time = time;
        this.requestEditType = requestEditType;
        this.reason = reason;
        this.note = note;
        this.attendanceMachineId = attendanceMachineId;
    }

    public int getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public LocalDate getDay() {
        return day;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getRequestEditType() {
        return requestEditType;
    }

    public String getAttendanceMachineId() {
        return attendanceMachineId;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setType(String type) {
        this.requestEditType = type;
    }

    public String getDayOfWeek() {
        return day.getDayOfWeek().toString();
    }

    public String getReason(){
        return reason;
    }

    public void setReason(String reason){
        this.reason = reason;
    }

    public String getNote(){
        return note;
    }
}
