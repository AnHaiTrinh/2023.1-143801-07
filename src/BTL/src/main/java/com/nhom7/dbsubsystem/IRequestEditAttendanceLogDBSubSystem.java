package com.nhom7.dbsubsystem;

import com.nhom7.entity.RequestEditAttendanceLog;

import java.time.LocalDate;
import java.util.List;

public interface IRequestEditAttendanceLogDBSubSystem {
    public RequestEditAttendanceLog getRequestEditAttendanceLogById(int id);
    public boolean addRequestEditAttendanceLog(RequestEditAttendanceLog requestEditAttendanceLog);
}
