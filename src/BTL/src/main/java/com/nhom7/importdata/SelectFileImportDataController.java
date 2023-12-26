package com.nhom7.importdata;

import com.nhom7.dbsubsystem.RemoteAttendanceLogDBSubSystem;
import com.nhom7.dbsubsystem.RemoteHistoryImportFileDBSystem;
import com.nhom7.entity.AttendanceLog;
import com.nhom7.entity.HistoryImportFile;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SelectFileImportDataController implements Initializable {
    private FileChooser fileChooser;
    public CheckFileExcel checkFileExcel;
    public TableView tableView;
    public List<TableColumn> tableColumnList = new ArrayList<TableColumn>();
    public TableColumn maNV, ngay, gio, loai, tenNV, phongBan;
    public TextField labelPathToFile;
    public Button buttonAcceptImport;
    static final FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("data file", "*.csv", "*.xlsx");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnList.add(maNV);
        tableColumnList.add(tenNV);
        tableColumnList.add(ngay);
        tableColumnList.add(gio);
        tableColumnList.add(loai);
        tableColumnList.add(phongBan);
        buttonAcceptImport.setDisable(true);
    }
    public SelectFileImportDataController(FileChooser fileChooser, CheckFileExcel checkFileExcel) {
        this.fileChooser = fileChooser;
        this.checkFileExcel = checkFileExcel;
    }

    public  void  onMousePressedButtonAcceptImport(MouseEvent event) throws IOException {
        String path = labelPathToFile.getText();
        ReadFileExcel readFileExcel = new ReadFileExcel();
        List<AttendanceLog> attendanceLogList = readFileExcel.readDataFromFile(path);
        RemoteAttendanceLogDBSubSystem remoteAttendanceLogDBSubSystem = new RemoteAttendanceLogDBSubSystem();
        if (attendanceLogList!= null){
            for (AttendanceLog attendanceLog: attendanceLogList){
                remoteAttendanceLogDBSubSystem.addAttendanceLog(attendanceLog);
            }
            HistoryImportFile historyImportFile = new HistoryImportFile(java.time.LocalDate.now(),
                    java.time.LocalTime.now(), attendanceLogList.size());
            RemoteHistoryImportFileDBSystem remoteHistoryImportFileDBSystem = new RemoteHistoryImportFileDBSystem();
            remoteHistoryImportFileDBSystem.addHistoryImportFile(historyImportFile);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Import thành công");
            alert.showAndWait();
        }
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