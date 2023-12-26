package com.nhom7.request_edit;

import com.nhom7.config.Settings;
import com.nhom7.dbsubsystem.IRequestEditAttendanceLogDBSubSystem;
import com.nhom7.dbsubsystem.MemoryRequestEditAttendanceLogDBSubSystem;
import com.nhom7.entity.Employee;
import com.nhom7.entity.RequestEditAttendanceLog;
import com.nhom7.hrsubsystem.IHRSubSystem;
import com.nhom7.hrsubsystem.MemoryHRSubSystem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class RequestEditAttendanceLogControllerTest extends ApplicationTest {
    private RequestEditAttendanceLogController controller;
    private final IRequestEditAttendanceLogDBSubSystem dbSubSystem = new MemoryRequestEditAttendanceLogDBSubSystem();
    private final IHRSubSystem hrSubSystem = new MemoryHRSubSystem();
    private final Employee employee1 = new Employee(
            "20200260",
            "Nguyen Kim Hung",
            "Head of Department",
            "Production Factory"
    );
    private final Employee employee2 = new Employee(
        "20200673",
        "Le Anh Vu",
        "HR Manager",
        "Human Resource"
    );
    private int idCount = 10;
    @Override
    public void start (javafx.stage.Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("request_edit_attendanceLog.fxml"));
        controller = new RequestEditAttendanceLogController(employee1.getId(), hrSubSystem, dbSubSystem);
        fxmlLoader.setController(controller);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    void assertAddRequestEditAttendanceLogIsDisplay(String date, String time, String requestEditAttendanceLogType, String attendanceMachineId){
        assertEquals(
                date,
                controller.datePicker.getValue().toString()
        );
        assertEquals(
                time,
                controller.timeTextField.getText()
        );
        assertEquals(
                requestEditAttendanceLogType,
                controller.requestEditAttendanceLogTypes.getValue()
        );
        assertEquals(
                attendanceMachineId,
                controller.attendanceMachineId.getValue()
        );
    }

    void assertAddRequestEditAttendanceLogIsDisplay(String date, String time, String timeChange, String requestEditAttendanceLogType, String attendanceMachineId){
        assertEquals(
                date,
                controller.datePicker.getValue().toString()
        );
        assertEquals(
                time,
                controller.timeTextField.getText()
        );
        assertEquals(
                timeChange,
                controller.timeTextFieldChange.getText()
        );
        assertEquals(
                requestEditAttendanceLogType,
                controller.requestEditAttendanceLogTypes.getValue()
        );
        assertEquals(
                attendanceMachineId,
                controller.attendanceMachineId.getValue()
        );
    }

    void assertRequestEditAttendanceLogIsSync(RequestEditAttendanceLog newRequestEditAttendanceLog){

        RequestEditAttendanceLog requestEditAttendanceLog = dbSubSystem.getRequestEditAttendanceLogById(idCount);
        assertEquals(
            requestEditAttendanceLog, newRequestEditAttendanceLog
        );
    }

    @Test
    void testCorrectEmployeeInfoLoaded(){
        assertEquals(
                controller.fullNameTextField.getText(),
                employee1.getName()
        );
        assertEquals(
                controller.staffCodeTextField.getText(),
                employee1.getId()
        );
    }

    private void enterAddRemoveInput(String date, String time, String requestEditAttendanceLogType, String attendanceMachineId){
        DatePicker datePicker = lookup("#datePicker").queryAs(DatePicker.class);
        interact(() -> datePicker.setValue(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        TextField timeTextField = lookup("#timeTextField").queryAs(TextField.class);
        interact(() -> timeTextField.setText(time));
        clickOn("#requestEditAttendanceLogTypes");
        clickOn(requestEditAttendanceLogType);
        clickOn("#attendanceMachineId");
        clickOn(attendanceMachineId);
    }

    private void enterEditInput(String date, String time, String timeChange, String requestEditAttendanceLogType, String attendanceMachineId){
        DatePicker datePicker = lookup("#datePicker").queryAs(DatePicker.class);
        interact(() -> datePicker.setValue(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        TextField timeTextField = lookup("#timeTextField").queryAs(TextField.class);
        interact(() -> timeTextField.setText(time));
        TextField timeTextFieldChange = lookup("#timeTextFieldChange").queryAs(TextField.class);
        interact(() -> timeTextFieldChange.setText(timeChange));
        clickOn("#requestEditAttendanceLogTypes");
        clickOn(requestEditAttendanceLogType);
        clickOn("#attendanceMachineId");
        clickOn(attendanceMachineId);
    }

    private void assertAlertPopup(String alertContent){
        assertEquals(
                alertContent,
                lookup(LabeledMatchers.hasText(alertContent)).queryAs(Label.class).getText()
        );
    }

    // Test yêu cầu thêm/xóa chấm công
    @ParameterizedTest
    @CsvSource({
            "2022-12-23, 08:00:00, Thêm chấm công, 2",
            "2023-02-21, 18:09:24, Xóa chấm công, 1",
            "2022-07-03, 07:43:00, Thêm chấm công, 3",
            "2022-12-23, 07:43:00, Xóa chấm công, 3",
    })
    void testValidInput(String date, String time, String requestEditAttendanceLogType, String attendanceMachineId){
        enterAddRemoveInput(date, time, requestEditAttendanceLogType, attendanceMachineId);
        clickOn("#saveButton");
        assertAlertPopup("Khi bạn gửi, thông báo sẽ được gửi đến bộ phận nhân sự!");
        clickOn("OK");
        assertAlertPopup("Gửi yêu cầu thành công");
        clickOn("OK");

        assertAddRequestEditAttendanceLogIsDisplay(date, time, requestEditAttendanceLogType, attendanceMachineId);
    }

    @ParameterizedTest
    @CsvSource({
            "2023-06-04, 08:00:00, Thêm chấm công, 2",
    })
    void testSaveData(String date, String time, String requestEditAttendanceLogType, String attendanceMachineId){
        enterAddRemoveInput(date, time, requestEditAttendanceLogType, attendanceMachineId);
        clickOn("#saveButton");
        assertAlertPopup("Khi bạn gửi, thông báo sẽ được gửi đến bộ phận nhân sự!");
        clickOn("OK");
        idCount++;
        RequestEditAttendanceLog newRequestEditAttendanceLog = new RequestEditAttendanceLog(
                idCount,
                employee1.getId(),
                LocalDate.parse("2021-05-01", Settings.DATE_FORMATTER),
                LocalTime.parse("07:00:00", Settings.TIME_FORMATTER),
                null,
                "Thêm chấm công",
                "Quên chấm công",
                "",
                "1"
        );
        assertAddRequestEditAttendanceLogIsDisplay(date, time, requestEditAttendanceLogType, attendanceMachineId);
        boolean result = dbSubSystem.addRequestEditAttendanceLog(newRequestEditAttendanceLog);
        if (result){
            assertAlertPopup("Gửi yêu cầu thành công");
            clickOn("OK");
            assertRequestEditAttendanceLogIsSync(newRequestEditAttendanceLog);
        }
        else{
            assertAlertPopup("Không thể gửi yêu cầu");
            clickOn("OK");
        }
    }


    // Test yêu cầu chỉnh sửa chấm công
    @ParameterizedTest
    @CsvSource({
            "2023-01-01, 08:00:00, 07:59:00, Chỉnh sửa chấm công, 2",
            "2023-01-01, 11:54:13, 12:11:03, Chỉnh sửa chấm công, 1",
            "2023-01-01, 17:59:24, 18:02:12, Chỉnh sửa chấm công, 1",
    })
    void testValidInput(String date, String time, String timeChange, String requestEditAttendanceLogType, String attendanceMachineId){
        enterEditInput(date, time, timeChange, requestEditAttendanceLogType, attendanceMachineId);
        clickOn("#saveButton");
        assertAlertPopup("Khi bạn gửi, thông báo sẽ được gửi đến bộ phận nhân sự!");
        clickOn("OK");

        assertAlertPopup("Gửi yêu cầu thành công");
        clickOn("OK");

        assertAddRequestEditAttendanceLogIsDisplay(date, time, timeChange, requestEditAttendanceLogType, attendanceMachineId);
    }

    @ParameterizedTest
    @CsvSource({
            "2023-01-01, 08:00:00, 07:59:00, Chỉnh sửa chấm công, 2",
            "2023-01-01, 11:54:13, 12:11:03, Chỉnh sửa chấm công, 1",
            "2023-01-01, 17:59:24, 18:02:12, Chỉnh sửa chấm công, 1",
    })
    void testCancelSendRequestEditAttendanceLog(String date, String time, String timeChange, String requestEditAttendanceLogType, String attendanceMachineId) {
        enterEditInput(date, time, timeChange, requestEditAttendanceLogType, attendanceMachineId);
        clickOn("#saveButton");

        assertAlertPopup("Khi bạn gửi, thông báo sẽ được gửi đến bộ phận nhân sự!");

        clickOn("Cancel");

        assertAlertPopup("Yêu cầu đã bị hủy");

        clickOn("OK");

        assertAddRequestEditAttendanceLogIsDisplay(date, time, timeChange, requestEditAttendanceLogType, attendanceMachineId);
    }
}