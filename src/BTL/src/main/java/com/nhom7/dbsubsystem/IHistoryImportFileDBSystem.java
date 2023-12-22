package com.nhom7.dbsubsystem;

import com.nhom7.entity.HistoryImportFile;

import java.time.LocalDate;
import java.util.List;

public interface IHistoryImportFileDBSystem {
    public List<HistoryImportFile> getAllHistoryImportFiles();
    public boolean addHistoryImportFile(HistoryImportFile historyImportFile);
    public List<HistoryImportFile> filterHistoryImportFileByDay(LocalDate day);
}
