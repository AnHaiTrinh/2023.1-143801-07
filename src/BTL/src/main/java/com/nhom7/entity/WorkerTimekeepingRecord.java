package com.nhom7.entity;

public class WorkerTimekeepingRecord extends TimekeepingRecord{
    private double totalWorkingHours;
    private double totalOvertimeHours;

    public WorkerTimekeepingRecord(String idEmployee, String nameEmployee, String department, String month) {
        super(idEmployee, nameEmployee, department, month);
    }

    public WorkerTimekeepingRecord(String idEmployee, String nameEmployee, String department, String month, double totalWorkingHours, double totalOvertimeHours) {
        super(idEmployee, nameEmployee, department, month);
        this.totalWorkingHours = totalWorkingHours;
        this.totalOvertimeHours = totalOvertimeHours;
    }

    public double getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public void setTotalWorkingHours(int totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    public double getTotalOvertimeHours() {
        return totalOvertimeHours;
    }

    public void setTotalOvertimeHours(double totalOvertimeHours) {
        this.totalOvertimeHours = totalOvertimeHours;
    }
}
