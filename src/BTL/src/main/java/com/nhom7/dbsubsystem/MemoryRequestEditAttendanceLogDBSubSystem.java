package com.nhom7.dbsubsystem;

import com.nhom7.entity.RequestEditAttendanceLog;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryRequestEditAttendanceLogDBSubSystem implements IRequestEditAttendanceLogDBSubSystem{
    private static final List<RequestEditAttendanceLog> requestEditAttendanceLogList = new ArrayList<>(Arrays.asList(
            new RequestEditAttendanceLog(
                    1,
                    "20200673",
                    LocalDate.parse("2021-05-01"),
                    LocalTime.parse("07:00:00"),
                    "Thêm chấm công",
                    "Quên chấm công",
                    "",
                    "1"
            )
    ));
    @Override
    public List<RequestEditAttendanceLog> getAllRequestEditAttendanceLogs() {
        return new ArrayList<>(requestEditAttendanceLogList);
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
        requestEditAttendanceLogList.add(requestEditAttendanceLog);
        return true;
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
