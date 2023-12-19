package com.nhom7.import_data;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SelectFileImportData {
    private FileChooser fileChooser;
    private CheckFileExcel checkFileExcel;
    static final FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("data file", "*.csv", "*.xlsx");

    public SelectFileImportData(FileChooser fileChooser, CheckFileExcel checkFileExcel) {
        this.fileChooser = fileChooser;
        this.checkFileExcel = checkFileExcel;
    }

    public  void  onMousePressedButtonAcceptImport(MouseEvent event) throws IOException {
    }
    public void onMousePressedButtonSelectFileImport(MouseEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fileChooser.setTitle("Chọn file import");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(stage);
        //String tes = checkFileExcel.checkErrorFile(file.toURI().toString());
        ReadFileExcel readFileExcel = new ReadFileExcel();
        readFileExcel.readDataFromFile(file.toURI().toString());
    }
}