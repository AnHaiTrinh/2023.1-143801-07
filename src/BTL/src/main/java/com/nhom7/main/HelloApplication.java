package com.nhom7.main;

import com.nhom7.exportTimekeepingRecord.ExportTimekeepingRecordView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExportTimekeepingRecordView.class.getResource("ExportTimekeepingRecord.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 864, 559);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}