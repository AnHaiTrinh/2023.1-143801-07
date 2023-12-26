package com.nhom7.import_data;

import com.nhom7.alert.AlertFactory;
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
            switch (result) {
                case "errorFormatMaNv":
                    AlertFactory.getInstance().createAlert("Error", "Cõ lỗi định dạng mã nhân viên");
                    break;
                case "errorFormatDay":
                    AlertFactory.getInstance().createAlert("Error", "Cõ lỗi định dạng ngày");
                    break;
                case "errorFormatTime":
                    AlertFactory.getInstance().createAlert("Error", "Cõ lỗi định dạng giờ");
                    break;
                case "errorFormatType":
                    AlertFactory.getInstance().createAlert("Error", "Cõ lỗi định dạng kiểu");
                    break;
                case "errorFormatMachineID":
                    AlertFactory.getInstance().createAlert("Error", "Cõ lỗi định dạng mã máy");
                    break;
                case "errorNull":
                    AlertFactory.getInstance().createAlert("Error", "Lỗi đường dẫn không xác định");
                    break;
                default:
                    AlertFactory.getInstance().createAlert("Error", "Lỗi không xác định");
                    break;
            }

            return null;
        }
    }
}
