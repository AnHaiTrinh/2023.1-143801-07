package com.nhom7.import_data;

import java.io.*;
import java.net.URL;
import java.util.Iterator;

import com.nhom7.check_value.IsValidValue;
import org.apache.poi.ss.usermodel.*;

public class CheckFileExcel implements CheckFileService {
    IsValidValue isValidValue = new IsValidValue();
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
                } else if (!isValidValue.isValidLocalDate(row.getCell(1).getStringCellValue())){
                    return "errorFormatDay";
                } else if (!isValidValue.isValidLocaTime(row.getCell(2).getStringCellValue())) {
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
