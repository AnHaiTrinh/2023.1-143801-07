package com.nhom7.edit;

import com.nhom7.dbsubsystem.IDBSubSystem;
import com.nhom7.entity.AttendanceLog;
import com.nhom7.entity.Employee;
import com.nhom7.hrsubsystem.IHRSubSystem;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditAttendanceLogController implements Initializable {
    public Label employeeIdLabel;
    public Label departmentLabel;
    public Label dayLabel;
    public ComboBox<String> typeComboBox;
    public Label nameLabel;
    public Label dowLabel;
    public Label machineIdLabel;
    public TextField timeTextField;
    public Button saveButton;
    public Button cancelButton;
    private AttendanceLog attendanceLog;

    private final IDBSubSystem dbSubSystem;

    private final IHRSubSystem hrSubSystem;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final ArrayList<String> attendanceLogTypes = new ArrayList<>(
            List.of("CHECKIN", "CHECKOUT")
    );

    public EditAttendanceLogController(AttendanceLog attendanceLog, IDBSubSystem dbSubSystem, IHRSubSystem hrSubSystem) {
        this.attendanceLog = attendanceLog;
        this.dbSubSystem = dbSubSystem;
        this.hrSubSystem = hrSubSystem;
    }

    public void handleSaveButtonClicked() {
        boolean validated = validateInput();
        if (!validated) {
            displayErrorMessage("Invalid input");
            rollback();
            return;
        }
        boolean confirmed = displayConfirmationMessage("Are you sure you want to save?");
        if (!confirmed) {
            displayInformationMessage("Operation Cancelled");
            rollback();
            return;
        }
        boolean saved = save();
        if (!saved) {
            displayErrorMessage("Cannot save attendance log");
            rollback();
            return;
        }
        displayInformationMessage("Saved successfully");
    }

    private void rollback() {
        typeComboBox.setValue(attendanceLog.getType());
        timeTextField.setText(attendanceLog.getTime().format(TIME_FORMATTER));
    }

    public void handleCancelButtonClicked() {
        boolean confirmed = displayConfirmationMessage("Are you sure you want to exit?");
        if (!confirmed) {
            displayInformationMessage("Operation Cancelled");
            return;
        }
        cancel();
    }

    public boolean validateInput() {
        String attendanceLogType = typeComboBox.getValue();
        if (!attendanceLogTypes.contains(attendanceLogType)) {
            return false;
        }
        try {
            LocalTime.parse(timeTextField.getText(), TIME_FORMATTER);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean save() {
        AttendanceLog updatedAttendanceLog = new AttendanceLog(
                attendanceLog.getId(),
                attendanceLog.getEmployeeId(),
                attendanceLog.getDay(),
                LocalTime.parse(timeTextField.getText(), TIME_FORMATTER),
                typeComboBox.getValue(),
                attendanceLog.getAttendanceMachineId()
        );
        boolean result = dbSubSystem.updateAttendanceLog(updatedAttendanceLog);
        if (result) {
            attendanceLog = updatedAttendanceLog;
        }
        return result;
    }

    private void displayErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Save unsuccessfully");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean displayConfirmationMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText(message);
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }

    private void displayInformationMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void cancel() {

    }

    public void loadInitialData(){
        String employeeId = attendanceLog.getEmployeeId();
        Employee employee = hrSubSystem.getEmployeeById(employeeId);
        if (employee == null) {
            displayErrorMessage("Cannot find employee with ID " + employeeId);
            return;
        }
        employeeIdLabel.setText(employee.getId());
        nameLabel.setText(employee.getName());
        departmentLabel.setText(employee.getDepartment());
        dowLabel.setText(attendanceLog.getDayOfWeek());
        dayLabel.setText(attendanceLog.getDay().format(DATE_FORMATTER));
        timeTextField.setText(attendanceLog.getTime().format(TIME_FORMATTER));
        typeComboBox.getItems().addAll(attendanceLogTypes);
        typeComboBox.setValue(attendanceLog.getType());
        machineIdLabel.setText(attendanceLog.getAttendanceMachineId());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveButton.setOnKeyPressed(
                keyEvent -> {
                    if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                        handleSaveButtonClicked();
                    }
                }
        );
        cancelButton.setOnKeyPressed(
                keyEvent -> {
                    if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                        handleCancelButtonClicked();
                    }
                }
        );
        loadInitialData();
    }
}
