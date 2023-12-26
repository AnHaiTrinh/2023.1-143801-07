package com.nhom7.attendanceloglist;

import com.nhom7.alert.AlertFactory;
import com.nhom7.dbsubsystem.IAttendanceLogDBSubSystem;
import com.nhom7.dbsubsystem.RemoteAttendanceLogDBSubSystem;
import com.nhom7.edit.EditAttendanceLogController;
import com.nhom7.entity.AttendanceLog;
import com.nhom7.config.Settings;
import com.nhom7.hrsubsystem.DatabaseHRSubSystem;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceLogListController implements Initializable {
    public AnchorPane mainContainer;

    public ComboBox<String> displayComboBox;
    public TextField searchBar;
    public Button exportButton;
    public TableView<AttendanceLog> attendanceLogListTable;
    public TableColumn<AttendanceLog, String> dowColumn;
    public TableColumn<AttendanceLog, String> dayColumn;
    public TableColumn<AttendanceLog, String> timeColumn;
    public TableColumn<AttendanceLog, String> employeeIdColumn;
    public TableColumn<AttendanceLog, String> typeColumn;

    public TableColumn<AttendanceLog, Void> editColumn;
    public TableColumn<AttendanceLog, Void> deleteColumn;
    public Button searchButton;
    public DatePicker dateFilter;

    final static List<String> DISPLAY_MODES = Arrays.asList(
            "Chi tiết chấm công",
            "Thống kê chấm công"
    );

    public void setDbSubSystem(IAttendanceLogDBSubSystem dbSubSystem) {
        this.dbSubSystem = dbSubSystem;
    }

    private IAttendanceLogDBSubSystem dbSubSystem;

    public AttendanceLogListController(IAttendanceLogDBSubSystem dbSubSystem) {
        this.dbSubSystem = dbSubSystem;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayComboBox.getItems().addAll(DISPLAY_MODES);
        displayComboBox.getSelectionModel().select(0);

        dateFilter.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate localDate) {
                if (localDate != null) {
                    return localDate.format(Settings.DATE_FORMATTER);
                } else {
                    return null;
                }
            }

            @Override
            public LocalDate fromString(String s) {
                try {
                    return LocalDate.parse(s, Settings.DATE_FORMATTER);
                } catch (Exception e) {
                    return null;
                }
            }
        });

        dateFilter.setValue(LocalDate.now());

        dowColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getDayOfWeek())
        );
        dayColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getDay().format(Settings.DATE_FORMATTER))
        );
        timeColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getTime().format(Settings.TIME_FORMATTER))
        );
        employeeIdColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getEmployeeId())
        );
        typeColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getType())
        );
        typeColumn.setCellFactory(column -> new TextFieldTableCell<>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty)
                    setText(null);
                else {
                    setText(item);
                    getStyleClass().add(item.toLowerCase());
                }
            }
        });

        editColumn.setCellFactory(column -> new TableCell<>() {
            private final Button editButton = new Button("Sửa");
            {
                editButton.setOnAction(event -> {
                    AttendanceLog attendanceLog = getTableView().getItems().get(getIndex());
                    handleEditButtonClicked(attendanceLog);
                    attendanceLogListTable.refresh();
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty)
                    setGraphic(null);
                else
                    setGraphic(editButton);
            }
        });

        deleteColumn.setCellFactory(column -> new TableCell<>() {
            private final Button deleteButton = new Button("Xoá");
            {
                deleteButton.setOnAction(event -> {
                    AttendanceLog attendanceLog = getTableView().getItems().get(getIndex());
                    handleDeleteButtonClicked(attendanceLog);
                    attendanceLogListTable.refresh();
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty)
                    setGraphic(null);
                else
                    setGraphic(deleteButton);
            }
        });

        dateFilter.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue){
                dateFilter.setValue(dateFilter.getConverter().fromString(dateFilter.getEditor().getText()));
            }
        });

        attendanceLogListTable.getItems().addAll(dbSubSystem.getAllAttendanceLogs());
    }

    private void handleEditButtonClicked(AttendanceLog attendanceLog) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/nhom7/edit/EditAttendanceLog.fxml"));
        fxmlLoader.setController(new EditAttendanceLogController(
                attendanceLog,
                new RemoteAttendanceLogDBSubSystem(),
                new DatabaseHRSubSystem()
        ));
        try {
            Parent childScreen = fxmlLoader.load();
            Scene childScene = new Scene(childScreen);
            Stage primaryStage = (Stage) mainContainer.getScene().getWindow();

            Stage childStage = new Stage();
            childStage.setScene(childScene);
            childStage.setTitle("Sửa log chấm công");
            childStage.initStyle(StageStyle.UNDECORATED); // Remove default buttons
            childStage.initOwner(primaryStage);

            mainContainer.setDisable(true);
            childStage.showAndWait();
            mainContainer.setDisable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteButtonClicked(AttendanceLog attendanceLog) {
        boolean confirmed = AlertFactory.getInstance().createAlertAndWaitForRespond("Confirmation",
                "Bạn có chắc chắn muốn xoá log chấm công này?"
        );
        if (!confirmed) return;
        boolean deleted = dbSubSystem.deleteAttendanceLog(attendanceLog);
        if(!deleted) {
            AlertFactory.getInstance().createAlertAndWaitForRespond("Error",
                    "Không thể xoá log chấm công này"
            );
            return;
        }
        attendanceLogListTable.getItems().remove(attendanceLog);
        attendanceLogListTable.refresh();
    }

    public void handleButtonExportClicked() {
        System.out.println("Export button clicked");
    }

    public void onActionPerformedComboBox() {
        String displayMode = displayComboBox.getValue();
        if (!displayMode.equals("Chi tiết chấm công")) {
            System.out.println("Changing scene ...");
        }
    }

    public void handleButtonSearchClicked() {
        LocalDate date = dateFilter.getValue();
        String employeeId = searchBar.getText();
        List<AttendanceLog> filteredAttendanceLogList = dbSubSystem.filterAttendanceLogByEmployeeIdAndDay(employeeId, date);
        attendanceLogListTable.getItems().clear();
        attendanceLogListTable.getItems().addAll(filteredAttendanceLogList);
    }
}
