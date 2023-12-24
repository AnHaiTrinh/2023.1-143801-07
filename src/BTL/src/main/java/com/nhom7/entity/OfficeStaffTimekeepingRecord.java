package com.nhom7.entity;

public class OfficeStaffTimekeepingRecord extends TimekeepingRecord{
    private int totalWorkSessions;
    private double totalArrivingLateOrLeavingEarlyHours;

    public OfficeStaffTimekeepingRecord(String idEmployee, String nameEmployee, String department, String month) {
        super(idEmployee, nameEmployee, department, month);
    }

    public OfficeStaffTimekeepingRecord(String idEmployee, String nameEmployee, String department, String month, int totalWorkSessions, double totalArrivingLateOrLeavingEarlyHours) {
        super(idEmployee, nameEmployee, department, month);
        this.totalWorkSessions = totalWorkSessions;
        this.totalArrivingLateOrLeavingEarlyHours = totalArrivingLateOrLeavingEarlyHours;
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
