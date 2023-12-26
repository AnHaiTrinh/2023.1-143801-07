package com.nhom7.entity;

public class OfficeStaffTimekeepingRecord {
    private String idEmployee;
    private String nameEmployee;
    private String department;
    private String month;
    private int totalWorkSessions;
    private double totalArrivingLateOrLeavingEarlyHours;

    public OfficeStaffTimekeepingRecord(String idEmployee, String nameEmployee, String department, String month, int totalWorkSessions, double totalArrivingLateOrLeavingEarlyHours) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.department = department;
        this.month = month;
        this.totalWorkSessions = totalWorkSessions;
        this.totalArrivingLateOrLeavingEarlyHours = totalArrivingLateOrLeavingEarlyHours;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotalWorkSessions() {
        return totalWorkSessions;
    }

    public void setTotalWorkSessions(int totalWorkSessions) {
        this.totalWorkSessions = totalWorkSessions;
    }

    public double getTotalArrivingLateOrLeavingEarlyHours() {
        return totalArrivingLateOrLeavingEarlyHours;
    }

    public void setTotalArrivingLateOrLeavingEarlyHours(double totalArrivingLateOrLeavingEarlyHours) {
        this.totalArrivingLateOrLeavingEarlyHours = totalArrivingLateOrLeavingEarlyHours;
    }
}
