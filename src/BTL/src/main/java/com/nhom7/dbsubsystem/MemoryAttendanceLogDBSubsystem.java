package com.nhom7.dbsubsystem;

import com.nhom7.entity.AttendanceLog;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryAttendanceLogDBSubsystem implements IAttendanceLogDBSubSystem {
    private static final List<AttendanceLog> attendanceLogList = new ArrayList<>(Arrays.asList(
            new AttendanceLog(
                    1,
                    "20200673",
                    LocalDate.parse("2021-05-01"),
                    LocalTime.parse("07:00:00"),
                    "CHECKIN",
                    "1"
            ),
            new AttendanceLog(
                    2,
                    "20200196",
                    LocalDate.parse("2021-05-01"),
                    LocalTime.parse("07:30:00"),
                    "CHECKIN",
                    "1"
            ),
            new AttendanceLog(
                    3,
                    "20200673",
                    LocalDate.parse("2021-05-01"),
                    LocalTime.parse("17:00:00"),
                    "CHECKOUT",
                    "1"
            ),
            new AttendanceLog(
                    4,
                    "20200196",
                    LocalDate.parse("2021-05-01"),
                    LocalTime.parse("17:18:02"),
                    "CHECKOUT",
                    "1"
            ),
            new AttendanceLog(
                    5,
                    "20200260",
                    LocalDate.parse("2021-05-02"),
                    LocalTime.parse("08:00:00"),
                    "CHECKIN",
                    "2"
            ),
            new AttendanceLog(
                    6,
                    "20200673",
                    LocalDate.parse("2021-05-02"),
                    LocalTime.parse("16:49:31"),
                    "CHECKOUT",
                    "2"
            )
    ));
    @Override
    public List<AttendanceLog> getAllAttendanceLogs() {
        return new ArrayList<>(attendanceLogList);
    }

    @Override
    public AttendanceLog getAttendanceLogById(int id) {
        for(AttendanceLog attendanceLog : attendanceLogList){
            if(attendanceLog.getId() == id)
                return attendanceLog;
        }
        return null;
    }

    @Override
    public List<AttendanceLog> filterAttendanceLogByEmployeeIdAndDay(String employeeId, LocalDate day) {
        ArrayList<AttendanceLog> filteredAttendanceLogList = new ArrayList<>();
        for(AttendanceLog attendanceLog : attendanceLogList){
            boolean employeeIdMatch = (employeeId == null || attendanceLog.getEmployeeId().contains(employeeId));
            boolean dayMatch = (day == null || attendanceLog.getDay().equals(day));
            if(employeeIdMatch && dayMatch)
                filteredAttendanceLogList.add(attendanceLog);
        }
        return filteredAttendanceLogList;
    }

    @Override
    public boolean addAttendanceLog(AttendanceLog attendanceLog) {
        attendanceLogList.add(attendanceLog);
        return true;
    }

    @Override
    public boolean updateAttendanceLog(AttendanceLog attendanceLog) {
        for(int i = 0; i < attendanceLogList.size(); i++){
            if(attendanceLogList.get(i).getId() == attendanceLog.getId()){
                attendanceLogList.set(i, attendanceLog);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAttendanceLog(AttendanceLog attendanceLog) {
        for(int i = 0; i < attendanceLogList.size(); i++){
            if(attendanceLogList.get(i).getId() == attendanceLog.getId()){
                attendanceLogList.remove(i);
                return true;
            }
        }
        return false;
    }
}
