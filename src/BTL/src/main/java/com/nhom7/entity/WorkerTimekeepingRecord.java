package com.nhom7.entity;

public class WorkerTimekeepingRecord extends TimekeepingRecord{
    private int totalWorkingHours;
    private int totalOvertimeHours;

    public WorkerTimekeepingRecord(Long idEmployee, String nameEmployee, String department, String month) {
        super(idEmployee, nameEmployee, department, month);
    }

    public WorkerTimekeepingRecord(Long idEmployee, String nameEmployee, String department, String month, int totalWorkingHours, int totalOvertimeHours) {
        super(idEmployee, nameEmployee, department, month);
        this.totalWorkingHours = totalWorkingHours;
        this.totalOvertimeHours = totalOvertimeHours;
    }

    public int getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public void setTotalWorkingHours(int totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    public int getTotalOvertimeHours() {
        return totalOvertimeHours;
    }

    public void setTotalOvertimeHours(int totalOvertimeHours) {
        this.totalOvertimeHours = totalOvertimeHours;
    }
}
