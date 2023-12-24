package com.nhom7.exportTimekeepingRecord;

import com.nhom7.entity.OfficeStaffTimekeepingRecord;
import com.nhom7.entity.WorkerTimekeepingRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        workerId.setCellValueFactory(new PropertyValueFactory<WorkerTimekeepingRecord, String>("Mã nhân viên"));
        workerName.setCellValueFactory(new PropertyValueFactory<WorkerTimekeepingRecord, String>("Họ tên"));
        workerUnit.setCellValueFactory(new PropertyValueFactory<WorkerTimekeepingRecord, String>("Đơn vị"));
        workerMonth.setCellValueFactory(new PropertyValueFactory<WorkerTimekeepingRecord, String>("Tháng"));
        workerWork.setCellValueFactory(new PropertyValueFactory<WorkerTimekeepingRecord, Double>("Tổng số giờ làm việc"));
        workerOvertime.setCellValueFactory(new PropertyValueFactory<WorkerTimekeepingRecord, Double>("Tổng số giờ tăng ca"));

        //officer table
        officerId.setCellValueFactory(new PropertyValueFactory<OfficeStaffTimekeepingRecord, String>("Mã nhân viên"));
        officerName.setCellValueFactory(new PropertyValueFactory<OfficeStaffTimekeepingRecord, String>("Họ tên"));
        officerUnit.setCellValueFactory(new PropertyValueFactory<OfficeStaffTimekeepingRecord, String>("Đơn vị"));
        officerMonth.setCellValueFactory(new PropertyValueFactory<OfficeStaffTimekeepingRecord, String>("Tháng"));
        officerSession.setCellValueFactory(new PropertyValueFactory<OfficeStaffTimekeepingRecord, Integer>("Tổng số giờ làm việc"));
        officerLate.setCellValueFactory(new PropertyValueFactory<OfficeStaffTimekeepingRecord, Double>("Tổng số giờ tăng ca"));

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
    }
}
