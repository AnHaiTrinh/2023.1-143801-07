package com.nhom7.exportTimekeepingRecord;

import com.nhom7.entity.OfficeStaffTimekeepingRecord;

import java.util.List;

public interface IOfficeStaffTimekeepingRecordRepository {
    public List<OfficeStaffTimekeepingRecord> getListOfficeStaffTimekeepingRecordsByUnitAndMonth(String unit, String month);
    public List<String> getAllOfficeStaffUnit();

}
