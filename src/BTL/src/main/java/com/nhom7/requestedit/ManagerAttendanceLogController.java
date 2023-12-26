package com.nhom7.requestedit;

import com.nhom7.dbsubsystem.MemoryRequestEditAttendanceLogDBSubSystem;
import com.nhom7.hrsubsystem.MemoryHRSubSystem;
import com.nhom7.screen.ScreenSwitch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerAttendanceLogController {
    public void onMousePressedButtonRequestEditAttendanceLog(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(RequestEditAttendanceLogController.class.getResource("request_edit_attendanceLog.fxml"));
//        String employeeId = "20200260";
//        fxmlLoader.setController(
//                new RequestEditAttendanceLogController(
//                        employeeId,
//                        new MemoryHRSubSystem(),
//                        new MemoryRequestEditAttendanceLogDBSubSystem()
//                )
//        );
//        Scene scene = new Scene(fxmlLoader.load(), 864, 559);
//        stage.setScene(scene);
//        stage.show();
        ScreenSwitch.switchScreen(
                stage,
                "/com/nhom7/requestedit/request_edit_attendanceLog.fxml",
                "Phần mềm quản lý chấm công",
                new RequestEditAttendanceLogController(
                        new MemoryHRSubSystem(),
                        new MemoryRequestEditAttendanceLogDBSubSystem()
                )
        );
    }
}
