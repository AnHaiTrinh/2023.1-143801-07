package com.nhom7.exportHelper;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ExportHelper {
    public static <T> void exportToExcel(TableView<T> tableView, String sheetName, String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(sheetName);
            ObservableList<TableColumn<T, ?>> columns = tableView.getColumns();
            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.size(); i++) {
                headerRow.createCell(i).setCellValue(columns.get(i).getText());
            }
            // Fill data
            ObservableList<T> items = tableView.getItems();
            System.out.println("itemsize: " + items.size());
            for (int rowIdx = 0; rowIdx < items.size(); rowIdx++) {
                Row row = sheet.createRow(rowIdx + 1);
                T item = items.get(rowIdx);
                for (int colIdx = 0; colIdx < columns.size(); colIdx++) {
                    Object cellValue = columns.get(colIdx).getCellData(item);
                    if (cellValue != null) {
                        row.createCell(colIdx).setCellValue(cellValue.toString());
                    }
                }
            }
            // Save the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }
    }

    public static <T> void exportToCsv(TableView<T> tableView, String sheetName, String filePath) throws IOException {
        ObservableList<TableColumn<T, ?>> columns = tableView.getColumns();
        ObservableList<T> data = tableView.getItems();
        StringBuilder csvContent = new StringBuilder();
        // Header row
        for (int i = 0; i < columns.size(); i++) {
            csvContent.append(columns.get(i).getText());
            if (i != columns.size() - 1) {
                csvContent.append(",");
            }
        }
        csvContent.append("\n");
        // Data rows
        for (T element : data) {
            for (TableColumn<T, ?> column : columns) {
                String cellValue = column.getCellData(element).toString();
                csvContent.append(cellValue).append(",");
            }
            csvContent.append("\n");
        }

        // Save file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(csvContent.toString());
            System.out.println("CSV file exported successfully");
        }
    }
}
