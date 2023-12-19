package com.nhom7.import_data;

import java.io.*;
import java.net.URL;
import java.util.Iterator;

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
                if(row.getCell(0).getCellType() != CellType.NUMERIC){
                    return "errorFormatMaNv";
                } else if (row.getCell(1).getCellType() != CellType.NUMERIC) {
                    return "errorFormatDay";
                } else if (row.getCell(2).getCellType() != CellType.NUMERIC) {
                    return  "errorFormatTime";
                } else if (row.getCell(3).getCellType() != CellType.STRING) {
                    return "errorFormatType";
                } else if (row.getCell(4).getCellType() != CellType.NUMERIC) {
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
