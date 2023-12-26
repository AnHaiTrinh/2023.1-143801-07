package com.nhom7.home;

import com.nhom7.dbsubsystem.MemoryRequestEditAttendanceLogDBSubSystem;
import com.nhom7.hrsubsystem.DatabaseHRSubSystem;
import com.nhom7.hrsubsystem.MemoryHRSubSystem;
import com.nhom7.requestedit.RequestEditAttendanceLogController;
import com.nhom7.screen.ScreenSwitch;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomePageController {
    public void onMousePressedButtonRequestEditAttendanceLog(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ScreenSwitch.switchScreen(
                stage,
                "/com/nhom7/requestedit/requestEditAttendanceLog.fxml",
                "Phần mềm quản lý chấm công",
                new RequestEditAttendanceLogController(
                        new MemoryRequestEditAttendanceLogDBSubSystem()
                )
        );
    }
}
