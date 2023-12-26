package com.nhom7.screen;

import com.nhom7.alert.AlertFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenSwitch {
    public static void switchScreen(Stage currentStage, String resourcePath, String title, Object controller) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ScreenSwitch.class.getResource(resourcePath));
            if (controller != null) fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            currentStage.setTitle(title);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.getInstance().createAlert("Error", "Lỗi hiển thị màn hình");
        }
    }
}
