package com.nhom7.edit;

import com.nhom7.dbsubsystem.ErrorAttendanceLogDBSubSystem;
import com.nhom7.dbsubsystem.IAttendanceLogDBSubSystem;
import com.nhom7.dbsubsystem.MemoryAttendanceLogDBSubsystem;
import com.nhom7.entity.AttendanceLog;
import com.nhom7.entity.Employee;
import com.nhom7.hrsubsystem.IHRSubSystem;
import com.nhom7.hrsubsystem.MemoryHRSubSystem;
import com.nhom7.config.Settings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testfx.framework.junit5.ApplicationTest;

import org.testfx.matcher.control.LabeledMatchers;


import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class EditAttendanceLogControllerTest extends ApplicationTest {
    private EditAttendanceLogController controller;

    private final IAttendanceLogDBSubSystem dbSubSystem = new MemoryAttendanceLogDBSubsystem();

    private final IHRSubSystem hrSubSystem = new MemoryHRSubSystem();

    private final Employee employee = new Employee(
            "20200673",
            "Le Anh Vu",
            "HR Manager",
            "Human Resource"
    );

    private final AttendanceLog attendanceLog = new AttendanceLog(
            1,
            "20200673",
            LocalDate.parse("2021-05-01", Settings.DATE_FORMATTER),
            LocalTime.parse("07:00:00", Settings.TIME_FORMATTER),
            "CHECKIN",
            "1"
    );

    @Override
    public void start (javafx.stage.Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditAttendanceLog.fxml"));
        controller = new EditAttendanceLogController(attendanceLog, dbSubSystem, hrSubSystem);
        fxmlLoader.setController(controller);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    void assertUpdatedAttendanceLogIsDisplayed(String time, String type) {
        assertEquals(
                time,
                controller.timeTextField.getText()
        );
        assertEquals(
                type,
                controller.typeComboBox.getValue()
        );
    }

    void assertNotUpdatedAttendanceLogIsDisplayed() {
        assertEquals(
                attendanceLog.getTime().format(Settings.TIME_FORMATTER),
                controller.timeTextField.getText()
        );
        assertEquals(
                attendanceLog.getType(),
                controller.typeComboBox.getValue()
        );
    }


    @Test
    void testCorrectEmployeeLoaded() {
        assertEquals(controller.employeeIdLabel.getText(), employee.getId());
        assertEquals(controller.nameLabel.getText(), employee.getName());
        assertEquals(controller.departmentLabel.getText(), employee.getDepartment());
    }

    @Test
    void testCorrectAttendanceLogLoaded() {
        assertEquals(
                controller.employeeIdLabel.getText(),
                attendanceLog.getEmployeeId());
        assertEquals(
                controller.dayLabel.getText(),
                attendanceLog.getDay().format(Settings.DATE_FORMATTER)
        );
        assertEquals(
                controller.timeTextField.getText(),
                attendanceLog.getTime().format(Settings.TIME_FORMATTER)
        );
        assertEquals(
                controller.machineIdLabel.getText(),
                attendanceLog.getAttendanceMachineId()
        );
        assertEquals(
                controller.typeComboBox.getValue(),
                attendanceLog.getType()
        );
        assertEquals(
                controller.dowLabel.getText(),
                attendanceLog.getDayOfWeek()
        );
    }

    private void enterInput(String time, String type) {
        TextField timeTextField = lookup("#timeTextField").queryAs(TextField.class);
        interact(() -> timeTextField.setText(time));
        clickOn("#typeComboBox");
        clickOn(type);
    }

    private void assertAlertPopup(String alertContent) {
        assertEquals(
                alertContent,
                lookup(LabeledMatchers.hasText(alertContent)).queryAs(Label.class).getText()
        );
    }



    @ParameterizedTest
    @CsvSource({
            "08:00:00, CHECKIN",
            "12:14:13, CHECKOUT",
            "18:09:24, CHECKIN",
    })
    void testCorrectAttendanceLogUpdated(String time, String type) {
        enterInput(time, type);

        clickOn("#saveButton");

        assertAlertPopup("Bạn chắc chắn muốn lưu thay đổi?");

        clickOn("OK");

        assertAlertPopup("Lưu thành công");

        clickOn("OK");

        assertUpdatedAttendanceLogIsDisplayed(time, type);
    }

    @ParameterizedTest
    @CsvSource({
            "7:00:00, CHECKOUT",
            "18h, CHECKOUT",
            "jhjkk, CHECKIN",
            "17:48:90, CHECKOUT",
    })
    void testInvalidInput(String time, String type) {
        enterInput(time, type);

        clickOn("#saveButton");

        assertAlertPopup("Đầu vào không hợp lệ");

        clickOn("OK");

        assertNotUpdatedAttendanceLogIsDisplayed();
    }

    @ParameterizedTest
    @CsvSource({
            "08:00:00, CHECKIN",
            "12:14:13, CHECKOUT",
            "18:09:24, CHECKIN",
    })
    void testCancelSaveAttendanceLog(String time, String type) {
       enterInput(time, type);

        clickOn("#saveButton");

        assertAlertPopup("Bạn chắc chắn muốn lưu thay đổi?");

        clickOn("Cancel");

        assertAlertPopup("Thao tác đã bị hủy");

        clickOn("OK");

        assertNotUpdatedAttendanceLogIsDisplayed();
    }

    @ParameterizedTest
    @CsvSource({
            "08:00:00, CHECKIN",
            "12:14:13, CHECKOUT",
            "18:09:24, CHECKIN",
    })
    void testDbSubSystemError(String time, String type) {
        controller.setDbSubSystem(new ErrorAttendanceLogDBSubSystem());

        enterInput(time, type);

        clickOn("#saveButton");

        assertAlertPopup("Bạn chắc chắn muốn lưu thay đổi?");

        clickOn("OK");

        assertAlertPopup("Không thể lưu thay đổi");

        clickOn("OK");

        assertNotUpdatedAttendanceLogIsDisplayed();
    }

    @Test
    void testExitButtonClick() {
        clickOn("#exitButton");

        assertAlertPopup("Bạn chắc chắn muốn thoát?");

        clickOn("OK");
    }

    @Test
    void testCancelExitButtonClick() {
        clickOn("#exitButton");

        assertAlertPopup("Bạn chắc chắn muốn thoát?");

        clickOn("Cancel");

        assertAlertPopup("Thao tác đã bị hủy");

        clickOn("OK");

        assertNotUpdatedAttendanceLogIsDisplayed();
    }
}
