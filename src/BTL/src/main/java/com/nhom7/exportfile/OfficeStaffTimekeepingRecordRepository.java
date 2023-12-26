package com.nhom7.exportfile;

import com.nhom7.dbsubsystem.DatabaseQuery;
import com.nhom7.entity.OfficeStaffTimekeepingRecord;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OfficeStaffTimekeepingRecordRepository implements IOfficeStaffTimekeepingRecordRepository{
    @Override
    public List<OfficeStaffTimekeepingRecord> getListOfficeStaffTimekeepingRecordsByUnitAndMonth(String unit, String month) {
        List<OfficeStaffTimekeepingRecord> list = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseQuery.executeQuery(
                    "SELECT * FROM office_staff_timekeeping_record WHERE department = ? AND month = ?;"
                    , unit, month);
            while(resultSet.next()){
                OfficeStaffTimekeepingRecord officeStaffTimekeepingRecord = new OfficeStaffTimekeepingRecord(
                        resultSet.getString("id_employee"),
                        resultSet.getString("name_employee"),
                        resultSet.getString("department"),
                        resultSet.getString("month"),
                        resultSet.getInt("total_work_sessions"),
                        resultSet.getDouble("total_arriving_late_or_leaving_early_hours")
                );
                list.add(officeStaffTimekeepingRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<String> getAllOfficeStaffUnit(){
        List<String> list = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseQuery.executeQuery(
                    "SELECT DISTINCT department FROM office_staff_timekeeping_record;"
            );
            while(resultSet.next()){
                list.add(resultSet.getString("department"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
