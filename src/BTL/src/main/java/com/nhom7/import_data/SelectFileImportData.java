package com.nhom7.import_data;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SelectFileImportData {
    public  void  onMousePressedButtonAcceptImport(MouseEvent event) throws IOException {

    }
    public void onMousePressedButtonSelectFileImport(MouseEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn file import");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("data file", "*.csv", "*.xlsx");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(stage);
        ReadFileExcel readFileExcel = new ReadFileExcel();
        readFileExcel.ReadFileData(file);
    }
}