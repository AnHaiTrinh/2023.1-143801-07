package com.nhom7.menu;
import com.nhom7.EmployeeContext;
import com.nhom7.attendanceloglist.AttendanceLogListController;
import com.nhom7.dbsubsystem.MemoryAttendanceLogDBSubsystem;
import com.nhom7.exportTimekeepingRecord.ExportTimekeepingRecordView;
import com.nhom7.import_data.ImportDataController;
import com.nhom7.login.LoginView;
import com.nhom7.screen.ScreenSwitch;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onMousePressedButtonImportData(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ScreenSwitch.switchScreen(
                stage,
                "/com/nhom7/import_data/HomeImportData.fxml",
                "Phần mềm quản lý chấm công",
                new ImportDataController()
        );
    }
    public void onMousePressedButtonManagerAttendanceLog(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ScreenSwitch.switchScreen(
                stage,
                "/com/nhom7/attendanceloglist/AttendanceLogList.fxml",
                "Phần mềm quản lý chấm công",
                new AttendanceLogListController(new MemoryAttendanceLogDBSubsystem())
        );
    }
    public void onMousePressedButtonOverView(MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ScreenSwitch.switchScreen(
                stage,
                "/com/nhom7/home/HomePage.fxml",
                "Phần mềm quản lý chấm công",
                null
        );
    }

    public void onMousePressedButtonExportData(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ScreenSwitch.switchScreen(
                stage,
                "/com/nhom7/exportTimekeepingRecord/ExportTimekeepingRecord.fxml",
                "Phần mềm quản lý chấm công",
                null
        );
    }

    public void onMousePressedButtonLogout(MouseEvent event) {
        EmployeeContext.destroy();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        LoginView loginView = new LoginView(stage);
        loginView.show();
    }
}
