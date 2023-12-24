package com.nhom7.exportTimekeepingRecord;

import com.nhom7.entity.OfficeStaffTimekeepingRecord;
import com.nhom7.entity.WorkerTimekeepingRecord;
import com.nhom7.exportHelper.ExportHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class ExportTimekeepingRecordController {

    @FXML
    private ComboBox<Integer> pickYear;
    @FXML
    private ComboBox<String> pickUnit;
    @FXML
    private ComboBox<Integer> pickMonth;
    @FXML
    private TableView<WorkerTimekeepingRecord> workerTable;
    @FXML
    private TableColumn<WorkerTimekeepingRecord, String> workerId;
    @FXML
    private TableColumn<WorkerTimekeepingRecord, String> workerName;
    @FXML
    private TableColumn<WorkerTimekeepingRecord, String> workerUnit;
    @FXML
    private TableColumn<WorkerTimekeepingRecord, String> workerMonth;
    @FXML
    private TableColumn<WorkerTimekeepingRecord, Double> workerWork;
    @FXML
    private TableColumn<WorkerTimekeepingRecord, Double> workerOvertime;
    @FXML
    private TableView<OfficeStaffTimekeepingRecord> officerTable;
    @FXML
    private TableColumn<OfficeStaffTimekeepingRecord, String> officerId;
    @FXML
    private TableColumn<OfficeStaffTimekeepingRecord, String> officerName;
    @FXML
    private TableColumn<OfficeStaffTimekeepingRecord, String> officerUnit;
    @FXML
    private TableColumn<OfficeStaffTimekeepingRecord, String> officerMonth;
    @FXML
    private TableColumn<OfficeStaffTimekeepingRecord, Integer>  officerSession;
    @FXML
    private TableColumn<OfficeStaffTimekeepingRecord, Double>  officerLate;
    @FXML
    private ComboBox<String> chooseTypeOfUnit;

    public void initialize() {
        //worker table
        workerId.setCellValueFactory(new PropertyValueFactory<WorkerTimekeepingRecord, String>("idEmployee"));
        workerName.setCellValueFactory(new PropertyValueFactory<WorkerTimekeepingRecord, String>("nameEmployee"));
        workerUnit.setCellValueFactory(new PropertyValueFactory<WorkerTimekeepingRecord, String>("department"));
        workerMonth.setCellValueFactory(new PropertyValueFactory<WorkerTimekeepingRecord, String>("month"));
        workerWork.setCellValueFactory(new PropertyValueFactory<WorkerTimekeepingRecord, Double>("totalWorkingHours"));
        workerOvertime.setCellValueFactory(new PropertyValueFactory<WorkerTimekeepingRecord, Double>("totalOvertimeHours"));

        //officer table
        officerId.setCellValueFactory(new PropertyValueFactory<OfficeStaffTimekeepingRecord, String>("idEmployee"));
        officerName.setCellValueFactory(new PropertyValueFactory<OfficeStaffTimekeepingRecord, String>("nameEmployee"));
        officerUnit.setCellValueFactory(new PropertyValueFactory<OfficeStaffTimekeepingRecord, String>("department"));
        officerMonth.setCellValueFactory(new PropertyValueFactory<OfficeStaffTimekeepingRecord, String>("month"));
        officerSession.setCellValueFactory(new PropertyValueFactory<OfficeStaffTimekeepingRecord, Integer>("totalWorkSessions"));
        officerLate.setCellValueFactory(new PropertyValueFactory<OfficeStaffTimekeepingRecord, Double>("totalArrivingLateOrLeavingEarlyHours"));

        //chooseTypeOfUnit
        chooseTypeOfUnit.getItems().addAll("Công nhân", "Nhân viên văn phòng");

        //pickMonth
        pickMonth.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);

        //pickYear
        pickYear.getItems().addAll(2020,2021,2022,2023,2024,2025,2026,2027,2028,2029);

        //pickUnit
        pickUnit.getItems().addAll(WorkerTimekeepingRecordRepository.getAllWorkerUnit());
        pickUnit.getItems().addAll(OfficeStaffTimekeepingRecordRepository.getAllOfficeStaffUnit());
    }


    public void chooseTypeOfUnit(ActionEvent actionEvent) {
        if(chooseTypeOfUnit.getValue().equals("Công nhân")){
            pickUnit.getItems().clear();
            pickUnit.getItems().addAll(WorkerTimekeepingRecordRepository.getAllWorkerUnit());
            workerTable.setVisible(true);
            officerTable.setVisible(false);
        }
        else if(chooseTypeOfUnit.getValue().equals("Nhân viên văn phòng")){
            pickUnit.getItems().clear();
            pickUnit.getItems().addAll(OfficeStaffTimekeepingRecordRepository.getAllOfficeStaffUnit());
            workerTable.setVisible(false);
            officerTable.setVisible(true);
        }
    }

    public void search(ActionEvent actionEvent) {
        //check if user choose all field
        if(pickUnit.getValue() == null || pickMonth.getValue() == null || pickYear.getValue() == null){
            //alert user to choose all field
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Bạn vui lòng chọn đầy đủ thông tin");
            alert.show();
            return;
        }
        String monthAndYear = pickMonth.getValue() + "/" + pickYear.getValue();
        //if user choose worker
        if(chooseTypeOfUnit.getValue().equals("Công nhân")){
            workerTable.getItems().clear();
            workerTable.getItems().addAll(WorkerTimekeepingRecordRepository.getListWorkerTimekeepingRecordsByUnitAndMonth(pickUnit.getValue(), monthAndYear));
        }
        //if user choose officer
        else if(chooseTypeOfUnit.getValue().equals("Nhân viên văn phòng")){
            officerTable.getItems().clear();
            officerTable.getItems().addAll(OfficeStaffTimekeepingRecordRepository.getListOfficeStaffTimekeepingRecordsByUnitAndMonth(pickUnit.getValue(), monthAndYear));
        }
    }

    public void exportExcel(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            if(chooseTypeOfUnit.getValue().equals("Công nhân")) {
                ExportHelper.exportToExcel(workerTable, "Sheet1", file.getAbsolutePath());
            }
            else if(chooseTypeOfUnit.getValue().equals("Nhân viên văn phòng")) {
                ExportHelper.exportToExcel(officerTable, "Sheet1", file.getAbsolutePath());
            }
        }
    }

    public void exportCsv(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Csv Files", "*.csv"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            if(chooseTypeOfUnit.getValue().equals("Công nhân")) {
                ExportHelper.exportToCsv(workerTable, "Sheet1", file.getAbsolutePath());
            }
            else if(chooseTypeOfUnit.getValue().equals("Nhân viên văn phòng")) {
                ExportHelper.exportToCsv(officerTable, "Sheet1", file.getAbsolutePath());
            }
        }
    }
}
