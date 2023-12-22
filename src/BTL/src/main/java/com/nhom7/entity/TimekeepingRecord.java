package com.nhom7.entity;

public abstract class TimekeepingRecord {
    private Long idEmployee;
    private String nameEmployee;
    private String department;
    private String month;

    public TimekeepingRecord(Long idEmployee, String nameEmployee, String department, String month) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.department = department;
        this.month = month;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
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
}
