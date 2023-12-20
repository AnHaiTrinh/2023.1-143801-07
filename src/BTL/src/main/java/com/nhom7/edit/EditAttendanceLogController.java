package com.nhom7.edit;

import com.nhom7.alert.AlertFactory;
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
import java.util.Arrays;
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
    public Button exitButton;
    private AttendanceLog attendanceLog;

    private IDBSubSystem dbSubSystem;

    private IHRSubSystem hrSubSystem;

    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    static final List<String> attendanceLogTypes = Arrays.asList("CHECKIN", "CHECKOUT");

    public EditAttendanceLogController(AttendanceLog attendanceLog, IDBSubSystem dbSubSystem, IHRSubSystem hrSubSystem) {
        this.attendanceLog = attendanceLog;
        this.dbSubSystem = dbSubSystem;
        this.hrSubSystem = hrSubSystem;
    }

    public void handleSaveButtonClicked() {
        boolean validated = validateInput();
        if (!validated) {
            AlertFactory.getInstance().createAlert("Error", "Invalid input");
            rollback();
            return;
        }
        boolean confirmed = AlertFactory.getInstance().
                createAlertAndWaitForRespond("Confirmation", "Are you sure you want to save?");
        if (!confirmed) {
            AlertFactory.getInstance().createAlert("Information", "Operation cancelled");
            rollback();
            return;
        }
        boolean saved = save();
        if (!saved) {
            AlertFactory.getInstance().createAlert("Error", "Cannot save attendance log");
            rollback();
            return;
        }
        AlertFactory.getInstance().createAlert("Information", "Saved successfully");
    }

    private void rollback() {
        typeComboBox.setValue(attendanceLog.getType());
        timeTextField.setText(attendanceLog.getTime().format(TIME_FORMATTER));
    }

    public void handleExitButtonClicked() {
        boolean confirmed = AlertFactory.getInstance().
                createAlertAndWaitForRespond("Confirmation", "Are you sure you want to exit?");
        if (!confirmed) {
            AlertFactory.getInstance().createAlert("Information", "Operation cancelled");
            return;
        }
        exit();
    }

    private boolean validateInput() {
        String updatedTime = timeTextField.getText();
        try {
            LocalTime.parse(updatedTime, TIME_FORMATTER);
        } catch (Exception e) {
            return false;
        }
        String updatedType = typeComboBox.getValue();
        return attendanceLogTypes.contains(updatedType);
    }

    private boolean save() {
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
            setAttendanceLog(updatedAttendanceLog);
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

    private void exit() {
        System.out.println("Exit");
    }

    private void loadInitialData(){
        String employeeId = attendanceLog.getEmployeeId();
        Employee employee = hrSubSystem.getEmployeeById(employeeId);
        if (employee == null) {
            AlertFactory.getInstance()
                    .createAlert("Error", "There is no employee with ID" + employeeId);
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
        exitButton.setOnKeyPressed(
                keyEvent -> {
                    if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                        handleExitButtonClicked();
                    }
                }
        );
        loadInitialData();
    }

    public void setAttendanceLog(AttendanceLog attendanceLog) {
        this.attendanceLog = attendanceLog;
    }

    public void setDbSubSystem(IDBSubSystem dbSubSystem) {
        this.dbSubSystem = dbSubSystem;
    }

    public void setHrSubSystem(IHRSubSystem hrSubSystem) {
        this.hrSubSystem = hrSubSystem;
    }
}
