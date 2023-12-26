package com.nhom7.exportfile;

import com.nhom7.entity.WorkerTimekeepingRecord;

import java.util.List;

public interface IWorkerTimekeepingRecordRepository {
    public List<WorkerTimekeepingRecord> getListWorkerTimekeepingRecordsByUnitAndMonth(String unit, String month);
    public List<String> getAllWorkerUnit();
}
