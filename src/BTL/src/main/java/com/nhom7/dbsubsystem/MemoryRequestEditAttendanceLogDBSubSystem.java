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
                    null,
                    "Thêm chấm công",
                    "Quên chấm công",
                    "",
                    "1"
            )
    ));

    @Override
    public RequestEditAttendanceLog getRequestEditAttendanceLogById(int id) {
        for (RequestEditAttendanceLog requestEditAttendanceLog : requestEditAttendanceLogList){
            if (requestEditAttendanceLog.getId() == id){
                return requestEditAttendanceLog;
            }
        }
        return null;
    }

    @Override
    public boolean addRequestEditAttendanceLog(RequestEditAttendanceLog requestEditAttendanceLog) {
        requestEditAttendanceLogList.add(requestEditAttendanceLog);
        return true;
    }
}
