package com.nhom7.import_data;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class ImportDataController {
    public void onMousePressedButtonImport(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ImportDataController.class.getResource("SelectFileImportData.fxml"));
        fxmlLoader.setController(new SelectFileImportData(new FileChooser(), new CheckFileExcel()));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}