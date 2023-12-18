package com.nhom7.import_data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ReadFileExcel {
    public ReadFileExcel() {

    }
    public void ReadFileData(File fileExcel) throws IOException {
        FileInputStream file = new FileInputStream(fileExcel);
        XSSFWorkbook _Workbook = new XSSFWorkbook (file);
        XSSFSheet _Sheet = _Workbook.getSheetAt (0);
        Iterator<Row> _RowIterator = _Sheet.iterator();

        while (((Iterator<?>) _RowIterator).hasNext()) {
            Row _Row = _RowIterator.next();
            Iterator<Cell> _CellIterator = _Row.cellIterator();

            while (_CellIterator.hasNext()) {
                Cell _Cell = _CellIterator.next();
                System.out.print(_Cell.toString() + "\t");
            }
            System.out.println();
        }
    }
}
