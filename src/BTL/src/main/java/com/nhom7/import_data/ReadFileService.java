package com.nhom7.import_data;

import com.nhom7.entity.AttendanceLog;

import java.io.IOException;
import java.util.List;

public interface ReadFileService {
    public List<AttendanceLog> readDataFromFile(String url) throws IOException;
}
