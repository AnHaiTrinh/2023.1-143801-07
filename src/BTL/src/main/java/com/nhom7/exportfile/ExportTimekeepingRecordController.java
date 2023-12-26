package com.nhom7.exportfile;

import com.nhom7.alert.AlertFactory;
import com.nhom7.entity.OfficeStaffTimekeepingRecord;
import com.nhom7.entity.WorkerTimekeepingRecord;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExportTimekeepingRecordController implements DynamicTable {

    @FXML
    public ComboBox<Integer> pickYear;
    @FXML
    public ComboBox<String> pickUnit;
    @FXML
    public ComboBox<Integer> pickMonth;
    @FXML
    public ComboBox<String> chooseTypeOfUnit = new ComboBox<>();
    @FXML
    public TableView<WorkerTimekeepingRecord> workerTable;
    @FXML
    public TableColumn<WorkerTimekeepingRecord, String> workerId = new TableColumn<>();
    @FXML
    public TableColumn<WorkerTimekeepingRecord, String> workerName = new TableColumn<>();
    @FXML
    public TableColumn<WorkerTimekeepingRecord, String> workerUnit = new TableColumn<>();
    @FXML
    public TableColumn<WorkerTimekeepingRecord, String> workerMonth = new TableColumn<>();
    @FXML
    public TableColumn<WorkerTimekeepingRecord, Double> workerWork = new TableColumn<>();
    @FXML
    public TableColumn<WorkerTimekeepingRecord, Double> workerOvertime = new TableColumn<>();
    @FXML
    public TableView<OfficeStaffTimekeepingRecord> officerTable;
    @FXML
    public TableColumn<OfficeStaffTimekeepingRecord, String> officerId = new TableColumn<>();
    @FXML
    public TableColumn<OfficeStaffTimekeepingRecord, String> officerName = new TableColumn<>();
    @FXML
    public TableColumn<OfficeStaffTimekeepingRecord, String> officerUnit = new TableColumn<>();
    @FXML
    public TableColumn<OfficeStaffTimekeepingRecord, String> officerMonth = new TableColumn<>();
    @FXML
    public TableColumn<OfficeStaffTimekeepingRecord, Integer>  officerSession = new TableColumn<>();
    @FXML
    public TableColumn<OfficeStaffTimekeepingRecord, Double>  officerLate = new TableColumn<>();

    public OfficeStaffTimekeepingRecordRepository officeStaffTimekeepingRecordRepository = new OfficeStaffTimekeepingRecordRepository();
    public WorkerTimekeepingRecordRepository workerTimekeepingRecordRepository = new WorkerTimekeepingRecordRepository();

    public void initialize() {
        //worker table
        workerId.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));
        workerName.setCellValueFactory(new PropertyValueFactory<>("nameEmployee"));
        workerUnit.setCellValueFactory(new PropertyValueFactory<>("department"));
        workerMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        workerWork.setCellValueFactory(new PropertyValueFactory<>("totalWorkingHours"));
        workerOvertime.setCellValueFactory(new PropertyValueFactory<>("totalOvertimeHours"));

        //officer table
        officerId.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));
        officerName.setCellValueFactory(new PropertyValueFactory<>("nameEmployee"));
        officerUnit.setCellValueFactory(new PropertyValueFactory<>("department"));
        officerMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        officerSession.setCellValueFactory(new PropertyValueFactory<>("totalWorkSessions"));
        officerLate.setCellValueFactory(new PropertyValueFactory<>("totalArrivingLateOrLeavingEarlyHours"));

        //chooseTypeOfUnit
        chooseTypeOfUnit.getItems().addAll("Công nhân", "Nhân viên văn phòng");

        //pickMonth
        pickMonth.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);

        //pickYear
        pickYear.getItems().addAll(2020,2021,2022,2023,2024,2025,2026,2027,2028,2029);

        //pickUnit
        pickUnit.getItems().addAll(workerTimekeepingRecordRepository.getAllWorkerUnit());
        pickUnit.getItems().addAll(officeStaffTimekeepingRecordRepository.getAllOfficeStaffUnit());
        pickUnit.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Chọn đơn vị");
                } else {
                    setText(item);
                }
            }
        });
    }


    public void chooseTypeOfUnit() {
        if(chooseTypeOfUnit.getValue().equals("Công nhân")){
            pickUnit.getItems().clear();
            pickUnit.getItems().addAll(workerTimekeepingRecordRepository.getAllWorkerUnit());
            setTable("Công nhân");
        }
        else if(chooseTypeOfUnit.getValue().equals("Nhân viên văn phòng")){
            pickUnit.getItems().clear();
            pickUnit.getItems().addAll(officeStaffTimekeepingRecordRepository.getAllOfficeStaffUnit());
            setTable("Nhân viên văn phòng");
        }
    }

    public void search() {
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
            List<WorkerTimekeepingRecord> list = workerTimekeepingRecordRepository.getListWorkerTimekeepingRecordsByUnitAndMonth(pickUnit.getValue(), monthAndYear);
            if(list.isEmpty()){
                AlertFactory.getInstance().createAlert("Information", "Không có dữ liệu theo yêu cầu của bạn");
            }else {
                workerTable.getItems().addAll(list);
            }
        }
        //if user choose officer
        else if(chooseTypeOfUnit.getValue().equals("Nhân viên văn phòng")){
            officerTable.getItems().clear();
            List<OfficeStaffTimekeepingRecord> list = officeStaffTimekeepingRecordRepository.getListOfficeStaffTimekeepingRecordsByUnitAndMonth(pickUnit.getValue(), monthAndYear);
            if (list.isEmpty()) {
                AlertFactory.getInstance().createAlert("Information", "Không có dữ liệu theo yêu cầu của bạn");
            } else {
                officerTable.getItems().addAll(list);
            }
        }
    }

    public void exportExcel() throws IOException {
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

    public void exportCsv() throws IOException {
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

    public void chooseUnit() {
        String unit = pickUnit.getValue();
        if (unit == null) return;
        List<String> listOfWorkerUnit = workerTimekeepingRecordRepository.getAllWorkerUnit();
        if(listOfWorkerUnit.contains(unit)){
            chooseTypeOfUnit.setValue("Công nhân");
        }
        else{
            chooseTypeOfUnit.setValue("Nhân viên văn phòng");
        }
        pickUnit.setValue(unit);
    }

    @Override
    public void setTable(String typeOfTable) {
        if(typeOfTable.equals("Công nhân")){
            workerTable.setVisible(true);
            officerTable.setVisible(false);
        }
        else if(typeOfTable.equals("Nhân viên văn phòng")){
            workerTable.setVisible(false);
            officerTable.setVisible(true);
        }
    }
}