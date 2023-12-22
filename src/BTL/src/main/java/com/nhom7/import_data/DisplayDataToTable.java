package com.nhom7.import_data;
import com.nhom7.entity.AttendanceLog;
import com.nhom7.hrsubsystem.DatabaseHRSubSystem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DisplayDataToTable {
    public void DisplayDataToTable (TableView tableViewer, List<AttendanceLog> attendanceLogList, List<TableColumn> columList) {
        attendanceLogList = FXCollections.observableArrayList(attendanceLogList);
        tableViewer.setItems(FXCollections.observableArrayList(attendanceLogList));
        columList.get(0).setCellValueFactory(new PropertyValueFactory<AttendanceLog, String>("employeeId"));
        TableColumn<AttendanceLog, String> columnName = columList.get(1);
        columnName.setCellValueFactory(cellDataFeatures -> {
            String employeeID = cellDataFeatures.getValue().getEmployeeId();
            String name = new DatabaseHRSubSystem().getEmployeeNameById(employeeID);
            return new SimpleStringProperty(name);
        });
        columList.get(2).setCellValueFactory(new PropertyValueFactory<AttendanceLog, LocalDate>("day"));
        columList.get(3).setCellValueFactory(new PropertyValueFactory<AttendanceLog, LocalTime>("time"));
        columList.get(4).setCellValueFactory(new PropertyValueFactory<AttendanceLog, String>("type"));
        columList.get(4).setCellFactory(column -> new TextFieldTableCell() {
            @Override
            public void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty)
                    setText(null);
                else {
                    String itemString = (String) item;
                    setText(itemString);
                    getStyleClass().add(itemString.toLowerCase());
                }
            }
        });
        TableColumn<AttendanceLog, String> columnPhongBan = columList.get(5);
        columnPhongBan.setCellValueFactory(cellDataFeatures -> {
            String employeeID = cellDataFeatures.getValue().getEmployeeId();
            String name = new DatabaseHRSubSystem().getEmployeeDepartmentByID(employeeID);
            return new SimpleStringProperty(name);
        });
    }
}
