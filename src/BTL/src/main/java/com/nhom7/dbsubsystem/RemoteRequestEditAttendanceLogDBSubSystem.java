package com.nhom7.dbsubsystem;

import com.nhom7.entity.RequestEditAttendanceLog;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class RemoteRequestEditAttendanceLogDBSubSystem implements IRequestEditAttendanceLogDBSubSystem{
    @Override
    public List<RequestEditAttendanceLog> getAllRequestEditAttendanceLogs() {
        return null;
    }

    @Override
    public RequestEditAttendanceLog getRequestEditAttendanceLogById(String id) {
        return null;
    }

    @Override
    public List<RequestEditAttendanceLog> filterRequestEditAttendanceLog(String employeeId, LocalDate day) {
        return null;
    }

    @Override
    public boolean addRequestEditAttendanceLog(RequestEditAttendanceLog requestEditAttendanceLog) {
        try {
            Connection connection = DBSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO request_edit_attendance_log " +
                            "(employee_id, day, time, requestEditType, reason, note, attendance_machine_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, requestEditAttendanceLog.getEmployeeId());
            statement.setDate(2, Date.valueOf(requestEditAttendanceLog.getDay()));
            statement.setTime(3, Time.valueOf(requestEditAttendanceLog.getTime()));
            statement.setString(4, requestEditAttendanceLog.getRequestEditType());
            statement.setString(5, requestEditAttendanceLog.getReason());
            statement.setString(6, requestEditAttendanceLog.getNote());
            statement.setString(7, requestEditAttendanceLog.getAttendanceMachineId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRequestEditAttendanceLog(RequestEditAttendanceLog requestEditAttendanceLog) {
        return false;
    }

    @Override
    public boolean deleteRequestEditAttendanceLog(RequestEditAttendanceLog requestEditAttendanceLog) {
        return false;
    }
}
