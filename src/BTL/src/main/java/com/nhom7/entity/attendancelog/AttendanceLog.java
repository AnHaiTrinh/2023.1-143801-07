package com.nhom7.entity.attendancelog;

import com.nhom7.entity.attendancelog.type.Type;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceLog {
    private final int id;
    private final String employeeId;
    private final LocalDate day;
    private LocalTime time;
    private Type type;

    private final String attendanceMachineId;

    public AttendanceLog(
            int id,
            String employeeId,
            LocalDate day,
            LocalTime time,
            Type type,
            String attendanceMachineId
    ) {
        this.id = id;
        this.employeeId = employeeId;
        this.day = day;
        this.time = time;
        this.type = type;
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

    public String getType() {
        return type.getType();
    }

    public String getAttendanceMachineId() {
        return attendanceMachineId;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
