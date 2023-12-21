package com.nhom7.edit;

import com.nhom7.alert.AlertFactory;
import com.nhom7.dbsubsystem.IAttendanceLogDBSubSystem;
import com.nhom7.entity.AttendanceLog;
import com.nhom7.entity.Employee;
import com.nhom7.hrsubsystem.IHRSubSystem;
import com.nhom7.config.Settings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class EditAttendanceLogController implements Initializable {
    public Pane mainEditPane;
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
    private final AttendanceLog attendanceLog;

    private IAttendanceLogDBSubSystem dbSubSystem;

    private IHRSubSystem hrSubSystem;

    static final List<String> attendanceLogTypes = Arrays.asList("CHECKIN", "CHECKOUT");

    public EditAttendanceLogController(AttendanceLog attendanceLog, IAttendanceLogDBSubSystem dbSubSystem, IHRSubSystem hrSubSystem) {
        this.attendanceLog = attendanceLog;
        this.dbSubSystem = dbSubSystem;
        this.hrSubSystem = hrSubSystem;
    }

    public void handleSaveButtonClicked() {
        boolean validated = validateInput();
        if (!validated) {
            AlertFactory.getInstance().createAlert("Error", "Đầu vào không hợp lệ");
            rollback();
            return;
        }
        boolean confirmed = AlertFactory.getInstance().
                createAlertAndWaitForRespond("Confirmation", "Bạn chắc chắn muốn lưu thay đổi?");
        if (!confirmed) {
            AlertFactory.getInstance().createAlert("Information", "Thao tác đã bị hủy");
            rollback();
            return;
        }
        boolean saved = save();
        if (!saved) {
            AlertFactory.getInstance().createAlert("Error", "Không thể lưu thay đổi");
            rollback();
            return;
        }
        AlertFactory.getInstance().createAlert("Information", "Lưu thành công");
    }

    private void rollback() {
        typeComboBox.setValue(attendanceLog.getType());
        timeTextField.setText(attendanceLog.getTime().format(Settings.TIME_FORMATTER));
    }

    public void handleExitButtonClicked() {
        boolean confirmed = AlertFactory.getInstance().
                createAlertAndWaitForRespond("Confirmation", "Bạn chắc chắn muốn thoát?");
        if (!confirmed) {
            AlertFactory.getInstance().createAlert("Information", "Thao tác đã bị hủy");
            return;
        }
        exit();
    }

    private boolean validateInput() {
        String updatedTime = timeTextField.getText();
        try {
            LocalTime.parse(updatedTime, Settings.TIME_FORMATTER);
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
                LocalTime.parse(timeTextField.getText(), Settings.TIME_FORMATTER),
                typeComboBox.getValue(),
                attendanceLog.getAttendanceMachineId()
        );
        boolean result = dbSubSystem.updateAttendanceLog(updatedAttendanceLog);
        if (result) {
            attendanceLog.setTime(updatedAttendanceLog.getTime());
            attendanceLog.setType(updatedAttendanceLog.getType());
        }
        return result;
    }

    private void exit() {
        ((Stage) mainEditPane.getScene().getWindow()).close();
    }

    private void loadInitialData(){
        String employeeId = attendanceLog.getEmployeeId();
        Employee employee = hrSubSystem.getEmployeeById(employeeId);
        if (employee == null) {
            AlertFactory.getInstance()
                    .createAlert("Error", "Không tìm thấy nhân viên với mã" + employeeId);
            return;
        }
        employeeIdLabel.setText(employee.getId());
        nameLabel.setText(employee.getName());
        departmentLabel.setText(employee.getDepartment());
        dowLabel.setText(attendanceLog.getDayOfWeek());
        dayLabel.setText(attendanceLog.getDay().format(Settings.DATE_FORMATTER));
        timeTextField.setText(attendanceLog.getTime().format(Settings.TIME_FORMATTER));
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

    public void setDbSubSystem(IAttendanceLogDBSubSystem dbSubSystem) {
        this.dbSubSystem = dbSubSystem;
    }

    public void setHrSubSystem(IHRSubSystem hrSubSystem) {
        this.hrSubSystem = hrSubSystem;
    }
}
