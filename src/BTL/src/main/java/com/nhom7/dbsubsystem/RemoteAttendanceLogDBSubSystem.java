package com.nhom7.dbsubsystem;

import com.nhom7.entity.AttendanceLog;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RemoteAttendanceLogDBSubSystem implements IAttendanceLogDBSubSystem {

    @Override
    public List<AttendanceLog> getAllAttendanceLogs() {
        try{
            ArrayList<AttendanceLog> attendanceLogList = new ArrayList<>();
            Connection connection = DBSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM attendance_log"
            );
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                AttendanceLog attendanceLog = new AttendanceLog(
                        rs.getInt("id"),
                        rs.getString("employee_id"),
                        rs.getDate("day").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getString("type"),
                        rs.getString("attendance_machine_id")
                );
                attendanceLogList.add(attendanceLog);
            }
            return attendanceLogList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AttendanceLog getAttendanceLogById(int id) {
        AttendanceLog attendanceLog = null;
        try{
            Connection connection = DBSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM attendance_log WHERE id = ?"
            );
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                attendanceLog = new AttendanceLog(
                        rs.getInt("id"),
                        rs.getString("employee_id"),
                        rs.getDate("day").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getString("type"),
                        rs.getString("attendance_machine_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceLog;
    }

    private PreparedStatement constructFilterQuery(Connection connection, String employeeId, LocalDate day) throws SQLException {
        PreparedStatement statement;
        if(employeeId == null || employeeId.isEmpty()) {
            if(day == null) {
                statement = connection.prepareStatement(
                        "SELECT * FROM attendance_log"
                );
            } else {
                statement = connection.prepareStatement(
                        "SELECT * FROM attendance_log WHERE day = ?"
                );
                statement.setDate(1, Date.valueOf(day));
            }

        } else {
            if(day == null) {
                statement = connection.prepareStatement(
                        "SELECT * FROM attendance_log WHERE employee_id ilike ?"
                );
                statement.setString(1, "%" + employeeId + "%");
            } else {
                statement = connection.prepareStatement(
                        "SELECT * FROM attendance_log WHERE employee_id = ? AND day = ?"
                );
                statement.setString(1, "%" + employeeId + "%");
                statement.setDate(2, Date.valueOf(day));
            }
        }
        return statement;
    }

    @Override
    public List<AttendanceLog> filterAttendanceLogByEmployeeIdAndDay(String employeeId, LocalDate day) {
        try {
            ArrayList<AttendanceLog> attendanceLogList = new ArrayList<>();
            Connection connection = DBSubsystemConnection.getConnection();
            PreparedStatement statement = constructFilterQuery(connection, employeeId, day);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                AttendanceLog attendanceLog = new AttendanceLog(
                        rs.getInt("id"),
                        rs.getString("employee_id"),
                        rs.getDate("day").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getString("type"),
                        rs.getString("attendance_machine_id")
                );
                attendanceLogList.add(attendanceLog);
            }
            return attendanceLogList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addAttendanceLog(AttendanceLog attendanceLog) {
        try {
            Connection connection = DBSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO attendance_log " +
                            "(employee_id, day, time, type, attendance_machine_id) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );
            statement.setString(1, attendanceLog.getEmployeeId());
            statement.setDate(2, Date.valueOf(attendanceLog.getDay()));
            statement.setTime(3, Time.valueOf(attendanceLog.getTime()));
            statement.setString(4, attendanceLog.getType());
            statement.setString(5, attendanceLog.getAttendanceMachineId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAttendanceLog(AttendanceLog attendanceLog) {
        try {
            Connection connection = DBSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE attendance_log SET " +
                            "time = ?, " +
                            "type = ? " +
                            "WHERE id = ?"
            );
            statement.setTime(1, Time.valueOf(attendanceLog.getTime()));
            statement.setString(2, attendanceLog.getType());
            statement.setInt(3, attendanceLog.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAttendanceLog(AttendanceLog attendanceLog) {
        try {
            Connection connection = DBSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM attendance_log WHERE id = ?"
            );
            statement.setInt(1, attendanceLog.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
