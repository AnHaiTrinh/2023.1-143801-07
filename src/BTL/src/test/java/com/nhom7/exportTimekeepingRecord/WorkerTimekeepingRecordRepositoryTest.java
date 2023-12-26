package com.nhom7.exportTimekeepingRecord;

import com.nhom7.dbsubsystem.DatabaseQuery;
import com.nhom7.entity.WorkerTimekeepingRecord;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkerTimekeepingRecordRepositoryTest {

    @Test
    void getListWorkerTimekeepingRecordsByUnitAndMonth() throws SQLException {
        List<WorkerTimekeepingRecord> list = new ArrayList<>();
        list.add(new WorkerTimekeepingRecord("testId1","testName1","testUnit","Month1",1,1));
        list.add(new WorkerTimekeepingRecord("testId2","testName2","testUnit","Month1",2,2));
        list.add(new WorkerTimekeepingRecord("testId3","testName3","testUnit","Month1",3,3));
        list.add(new WorkerTimekeepingRecord("testId4","testName4","testUnit","Month1",4,4));
        list.add(new WorkerTimekeepingRecord("testId5","testName5","testUnit","Month1",5,5));
        for(WorkerTimekeepingRecord workerTimekeepingRecord : list){
            DatabaseQuery.executeQuery(
                    "INSERT INTO worker_timekeeping_record(id_employee, name_employee, department, month, total_working_hours, total_overtime_hours) VALUES (?,?,?,?,?,?);",
                    workerTimekeepingRecord.getIdEmployee(),
                    workerTimekeepingRecord.getNameEmployee(),
                    workerTimekeepingRecord.getDepartment(),
                    workerTimekeepingRecord.getMonth(),
                    workerTimekeepingRecord.getTotalWorkingHours(),
                    workerTimekeepingRecord.getTotalOvertimeHours()
            );
        }
        ResultSet resultSet = DatabaseQuery.executeQuery(
                "SELECT * FROM worker_timekeeping_record WHERE department = ? AND month = ?;"
                , "testUnit", "Month1"
        );
        List<WorkerTimekeepingRecord> gotData = new ArrayList<>();
        while (resultSet.next()){
            gotData.add(new WorkerTimekeepingRecord(
                    resultSet.getString("id_employee"),
                    resultSet.getString("name_employee"),
                    resultSet.getString("department"),
                    resultSet.getString("month"),
                    resultSet.getDouble("total_working_hours"),
                    resultSet.getDouble("total_overtime_hours")
            ));
        }

        // Delete data
        for(WorkerTimekeepingRecord workerTimekeepingRecord : list){
            DatabaseQuery.executeQuery(
                    "DELETE FROM worker_timekeeping_record WHERE id_employee = ? AND name_employee = ? AND department = ? AND month = ? AND total_working_hours = ? AND total_overtime_hours = ?;",
                    workerTimekeepingRecord.getIdEmployee(),
                    workerTimekeepingRecord.getNameEmployee(),
                    workerTimekeepingRecord.getDepartment(),
                    workerTimekeepingRecord.getMonth(),
                    workerTimekeepingRecord.getTotalWorkingHours(),
                    workerTimekeepingRecord.getTotalOvertimeHours()
            );
        }
        assertEquals(list.size(),gotData.size());
    }

    @Test
    void getAllWorkerUnit() throws SQLException {
        List<String> allOldWorkerUnit = new ArrayList<>();
        ResultSet resultSet = DatabaseQuery.executeQuery(
                "SELECT DISTINCT department FROM worker_timekeeping_record;"
        );
        while(resultSet.next()){
            allOldWorkerUnit.add(resultSet.getString("department"));
        }
        List<String> newWorkerUnit = new ArrayList<>();
        newWorkerUnit.add("testUnit1");
        newWorkerUnit.add("testUnit2");
        newWorkerUnit.add("testUnit3");
        List<WorkerTimekeepingRecord> newUnitWorkerTimekeepingRecord = new ArrayList<>();
        newUnitWorkerTimekeepingRecord.add(new WorkerTimekeepingRecord("testId1","testName1","testUnit1","Month1",1,1));
        newUnitWorkerTimekeepingRecord.add(new WorkerTimekeepingRecord("testId2","testName2","testUnit2","Month1",2,2));
        newUnitWorkerTimekeepingRecord.add(new WorkerTimekeepingRecord("testId3","testName3","testUnit3","Month1",3,3));
        //add Data
        for(WorkerTimekeepingRecord workerTimekeepingRecord : newUnitWorkerTimekeepingRecord){
            DatabaseQuery.executeQuery(
                    "INSERT INTO worker_timekeeping_record(id_employee, name_employee, department, month, total_working_hours, total_overtime_hours) VALUES (?,?,?,?,?,?);",
                    workerTimekeepingRecord.getIdEmployee(),
                    workerTimekeepingRecord.getNameEmployee(),
                    workerTimekeepingRecord.getDepartment(),
                    workerTimekeepingRecord.getMonth(),
                    workerTimekeepingRecord.getTotalWorkingHours(),
                    workerTimekeepingRecord.getTotalOvertimeHours()
            );
        }
        List<String> allNewWorkerUnit = new ArrayList<>();
        ResultSet resultSet1 = DatabaseQuery.executeQuery(
                "SELECT DISTINCT department FROM worker_timekeeping_record;"
        );
        while(resultSet1.next()){
            allNewWorkerUnit.add(resultSet1.getString("department"));
        }
        // Delete data
        for(WorkerTimekeepingRecord workerTimekeepingRecord : newUnitWorkerTimekeepingRecord){
            DatabaseQuery.executeQuery(
                    "DELETE FROM worker_timekeeping_record WHERE id_employee = ? AND name_employee = ? AND department = ? AND month = ? AND total_working_hours = ? AND total_overtime_hours = ?;",
                    workerTimekeepingRecord.getIdEmployee(),
                    workerTimekeepingRecord.getNameEmployee(),
                    workerTimekeepingRecord.getDepartment(),
                    workerTimekeepingRecord.getMonth(),
                    workerTimekeepingRecord.getTotalWorkingHours(),
                    workerTimekeepingRecord.getTotalOvertimeHours()
            );
        }
        assertEquals(allOldWorkerUnit.size() + newWorkerUnit.size(), allNewWorkerUnit.size());
    }
}