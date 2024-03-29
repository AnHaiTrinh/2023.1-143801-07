package com.nhom7.requestedit;

import com.nhom7.EmployeeContext;
import com.nhom7.alert.AlertFactory;
import com.nhom7.config.Settings;
import com.nhom7.dbsubsystem.IRequestEditAttendanceLogDBSubSystem;
import com.nhom7.entity.RequestEditAttendanceLog;
import com.nhom7.hrsubsystem.IHRSubSystem;
import com.nhom7.screen.ScreenSwitch;
import com.nhom7.validate.DateTimeValidator;
import com.nhom7.validate.OptionValidator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.List;

public class RequestEditAttendanceLogController implements Initializable {
    public ComboBox<String> requestEditAttendanceLogTypes;
    public Button saveButton;
    public Button cancelButton;
    public Label timeChangeLabel;
    public TextField timeTextFieldChange;
    public TextField fullNameTextField;
    public TextField staffCodeTextField;
    public DatePicker datePicker;
    public TextField timeTextField;
    public TextField reasonTextField;
    public TextArea noteTextArea;
    public ComboBox<String> attendanceMachineId;
    private IRequestEditAttendanceLogDBSubSystem dbSubSystem;
    private List<String> requestEditAttendanceLogItems = Arrays.asList("Chỉnh sửa chấm công", "Thêm chấm công", "Xóa chấm công");
    private List<String> attendanceMachineIdItems = Arrays.asList("1", "2", "3");
    private int idCount = 5;
    public RequestEditAttendanceLogController(IRequestEditAttendanceLogDBSubSystem dbSubSystem){
        this.dbSubSystem = dbSubSystem;
    }
    public void onMousePressedButtonBackManagerAttendanceLog(MouseEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ScreenSwitch.switchScreen(
                stage,
                "/com/nhom7/home/HomePage.fxml",
                "Phần mềm quản lý chấm công",
                null
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveButton.setOnKeyPressed(
                keyEvent -> {
                    if (keyEvent.getCode().equals(KeyCode.ENTER)){
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

    private void loadInitialData(){
        fullNameTextField.setText(EmployeeContext.getName());
        staffCodeTextField.setText(EmployeeContext.getId());
        requestEditAttendanceLogTypes.getItems().addAll(requestEditAttendanceLogItems);
        //datePicker.setValue(LocalDate.now());
        if (!requestEditAttendanceLogItems.isEmpty()) {
            requestEditAttendanceLogTypes.setValue(requestEditAttendanceLogItems.get(0));
        }
        attendanceMachineId.getItems().addAll(attendanceMachineIdItems);
        if (!attendanceMachineIdItems.isEmpty()){
            attendanceMachineId.setValue(attendanceMachineIdItems.get(0));
        }
    }

    @FXML
    public void selectEditAttendanceLogType(){
        String selectValue = requestEditAttendanceLogTypes.getSelectionModel().getSelectedItem().toString();
        switch (selectValue){
            case "Chỉnh sửa chấm công":
                timeChangeLabel.setVisible(true);
                timeTextFieldChange.setVisible(true);
                break;
            case "Thêm chấm công":
                timeChangeLabel.setVisible(false);
                timeTextFieldChange.setVisible(false);
                break;
            case "Xóa chấm công":
                timeChangeLabel.setVisible(false);
                timeTextFieldChange.setVisible(false);
                break;
        }
    }

    public void handleSaveButtonClicked(){
        boolean validated = validateInput();
        if (!validated) {
            AlertFactory.getInstance().createAlert("Error", "Đầu vào không hợp lệ");
            return;
        }
        boolean confirmed = AlertFactory.getInstance().
                createAlertAndWaitForRespond("Confirmation", "Khi bạn gửi, thông báo sẽ được gửi đến bộ phận nhân sự!");
        if (!confirmed) {
            AlertFactory.getInstance().createAlert("Information", "Thao tác đã bị hủy");
            return;
        }
        boolean saved = save();
        if (!saved) {
            AlertFactory.getInstance().createAlert("Error", "Không thể gửi yêu cầu");
            return;
        }
        AlertFactory.getInstance().createAlert("Information", "Gửi yêu cầu thành công");
    }

    public void handleCancelButtonClicked() {
        boolean confirmed = AlertFactory.getInstance().
                createAlertAndWaitForRespond("Confirmation", "Bạn chắc chắn muốn thoát?");
        if (!confirmed) {
            AlertFactory.getInstance().createAlert("Information", "Thao tác đã bị hủy");
            return;
        }
        reset();
    }

    private void reset() {
        requestEditAttendanceLogTypes.setValue("Chỉnh sửa chấm công");
        reasonTextField.setText("");
        //datePicker.setValue(LocalDate.now());
        timeTextField.setText("hh:mm:ss");
        timeTextFieldChange.setVisible(true);
        timeTextFieldChange.setText("hh:mm:ss");
        timeChangeLabel.setVisible(true);
        timeChangeLabel.setText("Thay đổi thành");
        attendanceMachineId.setValue("1");
        noteTextArea.setText("");
    }

    private boolean validateInput() {
        boolean isValidTime = DateTimeValidator.isValidLocalTime(
                timeTextField.getText(),
                Settings.TIME_FORMATTER
        );
        if (!isValidTime) {
            return false;
        }
        return OptionValidator.isValidOption(
                requestEditAttendanceLogTypes.getValue(),
                requestEditAttendanceLogItems
        );
    }

    private boolean save() {
        RequestEditAttendanceLog requestEditAttendanceLog = new RequestEditAttendanceLog(
                idCount,
                EmployeeContext.getId(),
                datePicker.getValue(),
                LocalTime.parse(timeTextField.getText(), Settings.TIME_FORMATTER),
                !timeTextFieldChange.getText().isEmpty() ? LocalTime.parse(timeTextFieldChange.getText(), Settings.TIME_FORMATTER) : null,
                requestEditAttendanceLogTypes.getValue(),
                reasonTextField.getText(),
                noteTextArea.getText(),
                attendanceMachineId.getValue()
        );
        boolean result = dbSubSystem.addRequestEditAttendanceLog(requestEditAttendanceLog);
        idCount++;
        return result;
    }


    public void setDbSubSystem(IRequestEditAttendanceLogDBSubSystem dbSubSystem) {
        this.dbSubSystem = dbSubSystem;
    }
}