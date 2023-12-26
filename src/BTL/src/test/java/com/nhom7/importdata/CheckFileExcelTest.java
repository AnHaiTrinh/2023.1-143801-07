package com.nhom7.importdata;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CheckFileExcelTest {
    //White box testing
    CheckFileExcel checkFileExcel = new CheckFileExcel();
    @Test
    public void testReadFileDataFit() {
        // Set the file URL to point to the assets folder relatively to the project root
        String fileURL = new File("assets/Fit.xlsx").toURI().toString();
        try {
            assertEquals("success",checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    @Test
    public void testReadFileDataErrorDay() {
        // Replace with an actual URL of an Excel file for testing
        String fileURL = new File("assets/ErrorDay.xlsx").toURI().toString();
        try {
            assertEquals("errorFormatDay",checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    @Test
    public void testReadFileDataErrorMaNV() {
        // Replace with an actual URL of an Excel file for testing
        String fileURL = new File("assets/ErrorMaNV.xlsx").toURI().toString();
        try {
            assertEquals("errorFormatMaNv",checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    @Test
    public void testReadFileDataErrorTime() {
        // Replace with an actual URL of an Excel file for testing
        String fileURL = new File("assets/ErrorTime.xlsx").toURI().toString();
        try {
            assertEquals("errorFormatTime",checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    @Test
    public void testReadFileDataErrorType() {
        // Replace with an actual URL of an Excel file for testing
        String fileURL = new File("assets/ErrorType.xlsx").toURI().toString();
        try {
            assertEquals("errorFormatType",checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    @Test
    public void testReadFileDataErrorMachineID() {
        // Replace with an actual URL of an Excel file for testing
        String fileURL = new File("assets/ErrorMachineID.xlsx").toURI().toString();
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
        String fileURL = new File("error_url/Fit.xlsx").toURI().toString();
        try {
            assertEquals("errorURL", checkFileExcel.checkErrorFile(fileURL));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
}
