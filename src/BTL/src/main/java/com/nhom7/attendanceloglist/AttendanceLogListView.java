package com.nhom7.attendanceloglist;

import com.nhom7.dbsubsystem.MemoryAttendanceLogDBSubsystem;
import com.nhom7.dbsubsystem.RemoteAttendanceLogDBSubSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AttendanceLogListView extends Application {

        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AttendanceLogList.fxml"));
            fxmlLoader.setController(new AttendanceLogListController(
                    new MemoryAttendanceLogDBSubsystem()
            ));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Danh sách chấm công");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
}
