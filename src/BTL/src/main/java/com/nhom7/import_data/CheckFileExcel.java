package com.nhom7.import_data;

import java.io.*;
import java.net.URL;
import java.util.Iterator;

import com.nhom7.config.Settings;
import com.nhom7.validate.DateTimeValidator;
import org.apache.poi.ss.usermodel.*;

public class CheckFileExcel implements CheckFileService {
    @Override
    public String checkErrorFile(String url) throws IOException {
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
                if(row.getCell(0).getCellType() != CellType.STRING){
                    return "errorFormatMaNv";
                } else if (!DateTimeValidator.isValidLocalDate(row.getCell(1).getStringCellValue(), Settings.DATE_FORMATTER)){
                    return "errorFormatDay";
                } else if (!DateTimeValidator.isValidLocalTime(row.getCell(2).getStringCellValue(), Settings.TIME_FORMATTER)) {
                    return "errorFormatTime";
                } else if (row.getCell(3).getCellType() != CellType.STRING) {
                    return "errorFormatType";
                } else if (row.getCell(4).getCellType() != CellType.STRING) {
                    return "errorFormatMachineID";
                } else if(row.getCell(0).getCellType() == null || row.getCell(1).getCellType() == null || row.getCell(2).getCellType() == null || row.getCell(3).getCellType() == null || row.getCell(4).getCellType() == null){
                    return "errorNull";
                }
            }
            workbook.close();
            return "success";
        }
        catch (Exception e){
            return "errorURL";
        }
    }
}
