package com.nhom7.dbsubsystem;

import com.nhom7.entity.AttendanceLog;

import java.time.LocalDate;
import java.util.List;

public class ErrorDBSubSystem implements IDBSubSystem{
    @Override
    public List<AttendanceLog> getAllAttendanceLogs() {
        return null;
    }

    @Override
    public AttendanceLog getAttendanceLogById(int id) {
        return null;
    }

    @Override
    public List<AttendanceLog> filterAttendanceLogByEmployeeIdAndDay(String employeeId, LocalDate day) {
        return null;
    }

    @Override
    public boolean addAttendanceLog(AttendanceLog attendanceLog) {
        return false;
    }

    @Override
    public boolean updateAttendanceLog(AttendanceLog attendanceLog) {
        return false;
    }

    @Override
    public boolean deleteAttendanceLog(AttendanceLog attendanceLog) {
        return false;
    }
}
