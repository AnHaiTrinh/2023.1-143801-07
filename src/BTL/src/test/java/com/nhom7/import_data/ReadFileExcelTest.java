package com.nhom7.import_data;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ReadFileExcelTest {

    @Test
    public void testReadFileData() {
        ReadFileExcel readFileExcel = new ReadFileExcel();

        // Replace with an actual URL of an Excel file for testing
        String fileURL = "file:/C:/Users/Admin/Desktop/Test.xlsx";
        try {
            readFileExcel.readFileData(fileURL);
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
}
