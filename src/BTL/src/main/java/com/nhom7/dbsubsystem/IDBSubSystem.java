package com.nhom7.dbsubsystem;

import com.nhom7.entity.attendancelog.AttendanceLog;

import java.time.LocalDate;
import java.util.List;

public interface IDBSubSystem {
    public List<AttendanceLog> getAllAttendanceLogs();

    public AttendanceLog getAttendanceLogById(int id);


    public List<AttendanceLog> filterAttendanceLogByEmployeeIdAndDay(String employeeId, LocalDate day);

    public boolean addAttendanceLog(AttendanceLog attendanceLog);

    public boolean updateAttendanceLog(AttendanceLog attendanceLog);

    public boolean deleteAttendanceLog(AttendanceLog attendanceLog);
}
