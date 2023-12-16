package com.nhom7.repository;

import com.nhom7.entity.attendancelog.AttendanceLog;

import java.util.List;

public interface IAttendanceLogRepository {
    public List<AttendanceLog> getAllAttendanceLogs(int limit, int offset);

    public AttendanceLog getAttendanceLogById(int id);

    public List<AttendanceLog> filterAttendanceLogByEmployeeId(
            String employeeId, int limit, int offset
    );

    public List<AttendanceLog> filterAttendanceLogByDay(
            String day, int limit, int offset
    );

    public List<AttendanceLog> filterAttendanceLogByEmployeeIdAndDay(
            String employeeId, String day, int limit, int offset
    );

    public boolean addAttendanceLog(AttendanceLog attendanceLog);

    public boolean updateAttendanceLog(AttendanceLog attendanceLog);

    public boolean deleteAttendanceLog(AttendanceLog attendanceLog);
}
