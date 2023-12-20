package com.nhom7.import_data;

import com.nhom7.check_value.IsValidValue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Test extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Test.class.getResource("HomeImportData.fxml"));
        fxmlLoader.setController(new ImportDataController());
        Scene scene = new Scene(fxmlLoader.load(), 864, 559);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
