package com.nhom7.exportTimekeepingRecord;

import com.nhom7.dbsubsystem.DBSubsystemConnection;
import com.nhom7.dbsubsystem.DatabaseQuery;
import com.nhom7.entity.WorkerTimekeepingRecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerTimekeepingRecordRepository {
    public static List<WorkerTimekeepingRecord> getListWorkerTimekeepingRecordsByUnitAndMonth(String unit, String month){
        List<WorkerTimekeepingRecord> list = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseQuery.executeQuery(
                    "SELECT * FROM worker_timekeeping_record WHERE department = '?' AND month = '?';"
            , unit, month);
            while(resultSet.next()){
                WorkerTimekeepingRecord workerTimekeepingRecord = new WorkerTimekeepingRecord(
                        resultSet.getString("id_employee"),
                        resultSet.getString("name_employee"),
                        resultSet.getString("department"),
                        resultSet.getString("month"),
                        resultSet.getDouble("total_working_hours"),
                        resultSet.getDouble("total_overtime_hours")
                );
                list.add(workerTimekeepingRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<String> getAllWorkerUnit(){
        List<String> workerUnitList = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseQuery.executeQuery(
                    "SELECT DISTINCT department FROM worker_timekeeping_record;"
            );
            while(resultSet.next()){
                workerUnitList.add(resultSet.getString("department"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workerUnitList;
    }
}
