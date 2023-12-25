package com.nhom7.menu;
import com.nhom7.EmployeeContext;
import com.nhom7.attendanceloglist.AttendanceLogListController;
import com.nhom7.attendanceloglist.AttendanceLogListView;
import com.nhom7.dbsubsystem.MemoryAttendanceLogDBSubsystem;
import com.nhom7.home.HomePageView;
import com.nhom7.import_data.CheckFileExcel;
import com.nhom7.import_data.ImportDataController;
import com.nhom7.import_data.SelectFileImportData;
import com.nhom7.login.Login;
import com.nhom7.login.LoginController;
import com.nhom7.login.auth.MockAuthService;
import com.nhom7.request_edit_attendanceLog.ManagerAttendanceLogController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onMousePressedButtonImportData(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ImportDataController.class.getResource("HomeImportData.fxml"));
        fxmlLoader.setController(new ImportDataController());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    public void onMousePressedButtonManagerAttendanceLog(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(AttendanceLogListView.class.getResource("AttendanceLogList.fxml"));
        fxmlLoader.setController(new AttendanceLogListController(
                new MemoryAttendanceLogDBSubsystem()
        ));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Danh sách chấm công");
        stage.setScene(scene);
        stage.show();
    }
    public void onMousePressedButtonOverView(MouseEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ManagerAttendanceLogController.class.getResource("manager_attendanceLog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    public void onMousePressedButtonLogout(MouseEvent event) throws IOException {
        EmployeeContext.destroy();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("LoginPage.fxml"));
        fxmlLoader.setController(new LoginController(new MockAuthService()));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}
