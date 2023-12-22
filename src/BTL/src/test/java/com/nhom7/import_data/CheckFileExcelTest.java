package com.nhom7.import_data;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CheckFileExcelTest {
    //White box testing
    CheckFileExcel checkFileExcel = new CheckFileExcel();
    @Test
    public void testReadFileDataFit() {
        // Replace with an actual URL of an Excel file for testing
        String fileURL = "file:/C:/Users/Admin/Desktop/Test/Fit.xlsx";
        try {
            assertEquals("success",checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    @Test
    public void testReadFileDataErrorDay() {
        // Replace with an actual URL of an Excel file for testing
        String fileURL = "file:/C:/Users/Admin/Desktop/Test/ErrorDay.xlsx";
        try {
            assertEquals("errorFormatDay",checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    @Test
    public void testReadFileDataErrorMaNV() {
        // Replace with an actual URL of an Excel file for testing
        String fileURL = "file:/C:/Users/Admin/Desktop/Test/ErrorMaNV.xlsx";
        try {
            assertEquals("errorFormatMaNv",checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    @Test
    public void testReadFileDataErrorTime() {
        // Replace with an actual URL of an Excel file for testing
        String fileURL = "file:/C:/Users/Admin/Desktop/Test/ErrorTime.xlsx";
        try {
            assertEquals("errorFormatTime",checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    @Test
    public void testReadFileDataErrorType() {
        // Replace with an actual URL of an Excel file for testing
        String fileURL = "file:/C:/Users/Admin/Desktop/Test/ErrorType.xlsx";
        try {
            assertEquals("errorFormatType",checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    @Test
    public void testReadFileDataErrorMachineID() {
        // Replace with an actual URL of an Excel file for testing
        String fileURL = "file:/C:/Users/Admin/Desktop/Test/ErrorMachineID.xlsx";
        try {
            assertEquals("errorFormatMachineID",checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    //Black box testing
    @  Test
    public void testReadFileDataErrorURL() {
        // Replace with an actual URL of an Excel file for testing
        String fileURL = "com/nhom7/import_data/error_url/Fit.xlsx";
        try {
            assertEquals("errorURL",checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
}
