package com.nhom7.menu;
import com.nhom7.EmployeeContext;
import com.nhom7.attendanceloglist.AttendanceLogListController;
import com.nhom7.attendanceloglist.AttendanceLogListView;
import com.nhom7.dbsubsystem.MemoryAttendanceLogDBSubsystem;
import com.nhom7.exportTimekeepingRecord.ExportTimekeepingRecordView;
import com.nhom7.home.HomePageView;
import com.nhom7.import_data.ImportDataController;
import com.nhom7.login.Login;
import com.nhom7.login.LoginController;
import com.nhom7.login.auth.MockAuthService;
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
    public void onMousePressedButtonImportData(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ImportDataController.class.getResource("HomeImportData.fxml"));
        fxmlLoader.setController(new ImportDataController());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Phần mềm quản lý chấm công");
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
        stage.setTitle("Phần mềm quản lý chấm công");
        stage.setScene(scene);
        stage.show();
    }
    public void onMousePressedButtonOverView(MouseEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageView.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Phần mềm quản lý chấm công");
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

    public void onMousePressedButtonExportData(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ExportTimekeepingRecordView.class.getResource("ExportTimekeepingRecord.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Phần mềm quản lý chấm công");
        stage.setScene(scene);
        stage.show();
    }
}
