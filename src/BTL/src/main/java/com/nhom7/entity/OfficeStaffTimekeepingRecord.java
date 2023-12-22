package com.nhom7.entity;

public class OfficeStaffTimekeepingRecord extends TimekeepingRecord{
    private int totalWorkSessions;
    private int totalArrivingLateOrLeavingEarlyHours;

    public OfficeStaffTimekeepingRecord(Long idEmployee, String nameEmployee, String department, String month) {
        super(idEmployee, nameEmployee, department, month);
    }

    public OfficeStaffTimekeepingRecord(Long idEmployee, String nameEmployee, String department, String month, int totalWorkSessions, int totalArrivingLateOrLeavingEarlyHours) {
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

    public int getTotalArrivingLateOrLeavingEarlyHours() {
        return totalArrivingLateOrLeavingEarlyHours;
    }

    public void setTotalArrivingLateOrLeavingEarlyHours(int totalArrivingLateOrLeavingEarlyHours) {
        this.totalArrivingLateOrLeavingEarlyHours = totalArrivingLateOrLeavingEarlyHours;
    }
}
