package com.nhom7.import_data;
import com.nhom7.entity.AttendanceLog;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DisplayDataToTable {
    public void DisplayDataToTable (TableView tableViewer, List<AttendanceLog> attendanceLogList, List<TableColumn> columList) {
        attendanceLogList = FXCollections.observableArrayList(attendanceLogList);
        System.out.println(columList.get(0));
        columList.get(0).setCellValueFactory(new PropertyValueFactory<AttendanceLog, String>("employeeId"));
        columList.get(1).setCellValueFactory(new PropertyValueFactory<AttendanceLog, LocalDate>("day"));
        columList.get(2).setCellValueFactory(new PropertyValueFactory<AttendanceLog, LocalTime>("time"));
        columList.get(3).setCellValueFactory(new PropertyValueFactory<AttendanceLog, String>("type"));
        tableViewer.setItems(FXCollections.observableArrayList(attendanceLogList));
    }
}
