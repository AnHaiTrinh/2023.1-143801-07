package com.nhom7.import_data;

import java.io.*;
import java.net.URL;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ReadFileExcel implements FileReadService{
    public void readFileData(String url) throws IOException {
        System.out.println(url);
        try (InputStream inputStream = new URL(url).openStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            // Assuming only one sheet in the Excel file
            Sheet sheet = workbook.getSheetAt(0);
            // Loop through rows and cells to print the content
            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula() + "\t");
                            break;
                        case BLANK:
                            System.out.print("<BLANK>\t");
                            break;
                        default:
                            System.out.print("<UNKNOWN TYPE>\t");
                    }
                }
                System.out.println(); // Move to the next line after each row
            }

            workbook.close();
        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
            throw new IOException("Error reading the Excel file from URL: " + e.getMessage());
        }
    }

}
