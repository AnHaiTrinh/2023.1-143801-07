package com.nhom7.exportTimekeepingRecord;

import com.nhom7.attendanceloglist.AttendanceLogListController;
import com.nhom7.dbsubsystem.MemoryAttendanceLogDBSubsystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExportTimekeepingRecordView extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("alo " + System.getenv("DB_SUBSYSTEM_URL"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ExportTimekeepingRecord.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),864,559);
        stage.setTitle("Báo cáo chấm công");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
