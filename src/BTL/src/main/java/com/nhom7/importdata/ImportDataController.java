package com.nhom7.importdata;
import com.nhom7.dbsubsystem.RemoteHistoryImportFileDBSystem;
import com.nhom7.entity.HistoryImportFile;
import com.nhom7.screen.ScreenSwitch;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class ImportDataController implements Initializable {
    public TableView<HistoryImportFile> tableViewHistoryImportFile;
    public TableColumn tableColumnDayHistoryImportFile, tableColumnTimeHistoryImportFile,
            tableColumnTotalRecordHistoryImportFile;
    public DatePicker datePickerSearchHistoryImportFile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DisplayHistoryImportTable(new RemoteHistoryImportFileDBSystem().getAllHistoryImportFiles());
    }

    public void onMousePressedButtonImport(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ScreenSwitch.switchScreen(
                stage,
                "/com/nhom7/importdata/SelectFileImportData.fxml",
                "Chọn file",
                new SelectFileImportDataController(new FileChooser(), new CheckFileExcel())
        );
    }
    public void DisplayHistoryImportTable(List<HistoryImportFile> historyImportFiles){
        historyImportFiles = FXCollections.observableArrayList(historyImportFiles);
        tableViewHistoryImportFile.setItems(FXCollections.observableArrayList(historyImportFiles));
        tableColumnDayHistoryImportFile.setCellValueFactory(new PropertyValueFactory<HistoryImportFile, LocalDate>("day"));
        tableColumnTimeHistoryImportFile.setCellValueFactory(new PropertyValueFactory<HistoryImportFile, LocalTime>("time"));
        tableColumnTotalRecordHistoryImportFile.setCellValueFactory(new PropertyValueFactory<HistoryImportFile, Integer>("totalRecord"));
    }
    public void onMousePressedButtonSearchHistoryImportByDay(MouseEvent event){
        LocalDate day = datePickerSearchHistoryImportFile.getValue();
        List<HistoryImportFile> historyImportFiles = new RemoteHistoryImportFileDBSystem().filterHistoryImportFileByDay(day);
        DisplayHistoryImportTable(historyImportFiles);
    }
}