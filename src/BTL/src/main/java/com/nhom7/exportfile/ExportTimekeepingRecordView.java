package com.nhom7.exportfile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExportTimekeepingRecordView extends Application {

    @Override
    public void start(Stage stage) throws Exception {
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
