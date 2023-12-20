package com.nhom7.import_data;

import com.nhom7.entity.AttendanceLog;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadFileExcel implements ReadFileService{
    CheckFileExcel checkFileExcel = new CheckFileExcel();
    @Override
    public List<AttendanceLog> readDataFromFile(String url) throws IOException {
        List<AttendanceLog> attendanceLogList = new ArrayList<>();
        String result = checkFileExcel.checkErrorFile(url);
        if( result.equals("success")){
            try (InputStream inputStream = new URL(url).openStream()) {
                Workbook workbook = WorkbookFactory.create(inputStream);
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();
                boolean isFirstRow = true;
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    if (isFirstRow) {
                        isFirstRow = false;
                        continue;
                    }
                    String maNV = row.getCell(0).getStringCellValue();
                    LocalDate localDate = LocalDate.parse(row.getCell(1).getStringCellValue());
                    LocalTime localTime = LocalTime.parse(row.getCell(2).getStringCellValue());
                    String type = row.getCell(3).getStringCellValue();
                    String machineID = row.getCell(4).getStringCellValue();
                    AttendanceLog attendanceLog = new AttendanceLog(maNV, localDate, localTime, type, machineID);
                    attendanceLogList.add(attendanceLog);
                }
                workbook.close();
            }
            catch (Exception e){
                //e.printStackTrace();
            }
            return attendanceLogList;
        }else {
            ConsoleMessageDisplay consoleMessageDisplay = new ConsoleMessageDisplay();
            consoleMessageDisplay.displayMessage(result);
            return  null;
        }
    }
}
