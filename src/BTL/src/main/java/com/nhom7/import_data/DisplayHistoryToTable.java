package com.nhom7.import_data;

import com.nhom7.entity.AttendanceLog;
import com.nhom7.entity.HistoryImportFile;
import com.nhom7.hrsubsystem.DatabaseHRSubSystem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DisplayHistoryToTable {
    public void DisplayHistoryImportToTable (TableView tableViewer, List<HistoryImportFile> historyImportFiles, List<TableColumn> columList) {
        historyImportFiles = FXCollections.observableArrayList(historyImportFiles);
        tableViewer.setItems(FXCollections.observableArrayList(historyImportFiles));
        columList.get(0).setCellValueFactory(new PropertyValueFactory<HistoryImportFile, LocalDate>("day"));
        columList.get(1).setCellValueFactory(new PropertyValueFactory<HistoryImportFile, LocalTime>("time"));
        columList.get(2).setCellValueFactory(new PropertyValueFactory<HistoryImportFile, Integer>("totalRecord"));
    }
}
