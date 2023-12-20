package com.nhom7.import_data;

import com.nhom7.entity.AttendanceLog;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SelectFileImportData implements Initializable {
    private FileChooser fileChooser;
    public CheckFileExcel checkFileExcel;
    public TableView tableView;
    public List<TableColumn> tableColumnList = new ArrayList<TableColumn>();
    public TableColumn maNV, ngay, gio, loai;
    public TextField labelPathToFile;
    public Button buttonAcceptImport;
    static final FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("data file", "*.csv", "*.xlsx");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnList.add(maNV);
        //tableColumnList.add(tenNV);
        tableColumnList.add(ngay);
        tableColumnList.add(gio);
        tableColumnList.add(loai);
        buttonAcceptImport.setDisable(true);
    }
    public SelectFileImportData(FileChooser fileChooser, CheckFileExcel checkFileExcel) {
        this.fileChooser = fileChooser;
        this.checkFileExcel = checkFileExcel;
    }

    public  void  onMousePressedButtonAcceptImport(MouseEvent event) throws IOException {
        System.out.println(1);
    }
    public void onMousePressedButtonSelectFileImport(MouseEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fileChooser.setTitle("Chọn file import");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            ReadFileExcel readFileExcel = new ReadFileExcel();
            List<AttendanceLog> attendanceLogList = readFileExcel.readDataFromFile(file.toURI().toString());
            labelPathToFile.setText(file.toURI().toString());
            if(attendanceLogList != null){
                DisplayDataToTable displayDataToTable = new DisplayDataToTable();
                displayDataToTable.DisplayDataToTable(tableView, attendanceLogList, tableColumnList);
                buttonAcceptImport.setDisable(false);
            }
        }
    }
    public void onMousePressedButtonSearchPathFile(MouseEvent event) throws IOException{
        String path = labelPathToFile.getText();
        ReadFileExcel readFileExcel = new ReadFileExcel();
        List<AttendanceLog> attendanceLogList = readFileExcel.readDataFromFile(path);
        if(attendanceLogList != null){
            DisplayDataToTable displayDataToTable = new DisplayDataToTable();
            displayDataToTable.DisplayDataToTable(tableView, attendanceLogList, tableColumnList);
            buttonAcceptImport.setDisable(false);
        }
    }
}