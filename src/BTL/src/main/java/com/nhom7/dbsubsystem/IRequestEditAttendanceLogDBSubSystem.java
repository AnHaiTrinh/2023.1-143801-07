package com.nhom7.dbsubsystem;

import com.nhom7.entity.RequestEditAttendanceLog;

import java.time.LocalDate;
import java.util.List;

public interface IRequestEditAttendanceLogDBSubSystem {
    public List<RequestEditAttendanceLog> getAllRequestEditAttendanceLogs();
    public RequestEditAttendanceLog getRequestEditAttendanceLogById(String id);
    public List<RequestEditAttendanceLog> filterRequestEditAttendanceLog(String employeeId, LocalDate day);
    public boolean addRequestEditAttendanceLog(RequestEditAttendanceLog requestEditAttendanceLog);
    public boolean updateRequestEditAttendanceLog(RequestEditAttendanceLog requestEditAttendanceLog);
    public boolean deleteRequestEditAttendanceLog(RequestEditAttendanceLog requestEditAttendanceLog);
}
