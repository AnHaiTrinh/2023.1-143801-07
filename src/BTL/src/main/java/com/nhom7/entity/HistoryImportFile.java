package com.nhom7.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class HistoryImportFile {
    private int id;
    private LocalDate day;
    private LocalTime time;
    private int totalRecord;

    public HistoryImportFile(LocalDate day, LocalTime time, int totalRecord) {
        this.day = day;
        this.time = time;
        this.totalRecord = totalRecord;
    }

    public HistoryImportFile(int id, LocalDate day, LocalTime time, int totalRecord) {
        this.id = id;
        this.day = day;
        this.time = time;
        this.totalRecord = totalRecord;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
