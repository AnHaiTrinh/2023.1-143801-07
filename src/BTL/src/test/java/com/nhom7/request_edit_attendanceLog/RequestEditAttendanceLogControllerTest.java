package com.nhom7.request_edit_attendanceLog;

import com.nhom7.config.Settings;
import com.nhom7.dbsubsystem.IRequestEditAttendanceLogDBSubSystem;
import com.nhom7.dbsubsystem.MemoryRequestEditAttendanceLogDBSubSystem;
import com.nhom7.entity.Employee;
import com.nhom7.entity.RequestEditAttendanceLog;
import com.nhom7.hrsubsystem.IHRSubSystem;
import com.nhom7.hrsubsystem.MemoryHRSubSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RequestEditAttendanceLogControllerTest extends ApplicationTest {
    private RequestEditAttendanceLogController controller;
    private final IRequestEditAttendanceLogDBSubSystem dbSubSystem = new MemoryRequestEditAttendanceLogDBSubSystem();
    private final IHRSubSystem hrSubSystem = new MemoryHRSubSystem();
    private final Employee employee = new Employee(
            "20200260",
            "Nguyen Kim Hung",
            "Head of Department",
            "Production Factory"
    );
    private final RequestEditAttendanceLog requestEditAttendanceLog = new RequestEditAttendanceLog(
            0,
            employee.getId(),
            LocalDate.parse("2021-05-01", Settings.DATE_FORMATTER),
            LocalTime.parse("07:00:00", Settings.TIME_FORMATTER),
            "Thêm chấm công",
            "Quên chấm công",
            "",
            "1"
    );
    @Override
    public void start (javafx.stage.Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("request_edit_attendanceLog.fxml"));
        controller = new RequestEditAttendanceLogController(employee.getId(), hrSubSystem, dbSubSystem);
        fxmlLoader.setController(controller);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    void assertAddRequestEditAttendanceLogIsDisplay(String time, String requestEditAttendanceLogType){
        assertEquals(
                time,
                controller.timeTextField.getText()
        );
        assertEquals(
                requestEditAttendanceLogType,
                controller.requestEditAttendanceLogTypes.getValue()
        );
    }

    void assertNotAddRequestEditAttendanceLogIsDisplayed(){
        assertEquals(
                requestEditAttendanceLog.getTime().format(Settings.TIME_FORMATTER),
                controller.timeTextField.getText()
        );
        assertEquals(
                requestEditAttendanceLog.getRequestEditType(),
                controller.requestEditAttendanceLogTypes.getValue()
        );
    }

    @Test
    void testCorrectEmployeeInfoLoaded(){
        assertEquals(
                controller.fullNameTextField.getText(),
                employee.getName()
        );
        assertEquals(
                controller.staffCodeTextField.getText(),
                employee.getId()
        );
    }
}