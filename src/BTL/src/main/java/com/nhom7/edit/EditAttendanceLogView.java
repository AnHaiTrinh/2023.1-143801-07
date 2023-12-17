package com.nhom7.edit;

import com.nhom7.dbsubsystem.MemoryDBSubsystem;
import com.nhom7.hrsubsystem.MemoryHRSubSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EditAttendanceLogView extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditAttendanceLog.fxml"));
        fxmlLoader.setController(
                new EditAttendanceLogController(
                        new MemoryDBSubsystem().getAttendanceLogById(1),
                        new MemoryDBSubsystem(),
                        new MemoryHRSubSystem()
                )
        );
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Edit Attendance Log");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
