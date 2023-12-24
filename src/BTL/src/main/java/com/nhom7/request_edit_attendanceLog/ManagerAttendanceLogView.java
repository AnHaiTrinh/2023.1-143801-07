package com.nhom7.request_edit_attendanceLog;

import com.nhom7.dbsubsystem.MemoryRequestEditAttendanceLogDBSubSystem;
import com.nhom7.edit.EditAttendanceLogController;
import com.nhom7.hrsubsystem.MemoryHRSubSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManagerAttendanceLogView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ManagerAttendanceLogView.class.getResource("manager_attendanceLog.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 864, 559);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}