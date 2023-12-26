package com.nhom7.entity;

public class WorkerTimekeepingRecord {
    private String idEmployee;
    private String nameEmployee;
    private String department;
    private String month;
    private double totalWorkingHours;
    private double totalOvertimeHours;

    public WorkerTimekeepingRecord(String idEmployee, String nameEmployee, String department, String month, double totalWorkingHours, double totalOvertimeHours) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.department = department;
        this.month = month;
        this.totalWorkingHours = totalWorkingHours;
        this.totalOvertimeHours = totalOvertimeHours;
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

    public void setTotalWorkingHours(double totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    public double getTotalWorkingHours() {
        return totalWorkingHours;
    }


    public double getTotalOvertimeHours() {
        return totalOvertimeHours;
    }

    public void setTotalOvertimeHours(double totalOvertimeHours) {
        this.totalOvertimeHours = totalOvertimeHours;
    }
}
