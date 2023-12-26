package com.nhom7.request_edit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OverViewAttendanceLogView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(OverViewAttendanceLogView.class.getResource("overView_attendanceLog.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 864, 559);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}