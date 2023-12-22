package com.nhom7.import_data;
import com.nhom7.dbsubsystem.RemoteHistoryImportFileDBSystem;
import com.nhom7.entity.HistoryImportFile;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ImportDataController implements Initializable {
    public TableView<HistoryImportFile> tableViewHistoryImportFile;
    public TableColumn tableColumnDayHistoryImportFile, tableColumnTimeHistoryImportFile, tableColumnTotalRecordHistoryImportFile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DisplayHistoryImportTable();
    }

    public void onMousePressedButtonImport(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ImportDataController.class.getResource("SelectFileImportData.fxml"));
        fxmlLoader.setController(new SelectFileImportData(new FileChooser(), new CheckFileExcel()));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    public void DisplayHistoryImportTable(){
        List<HistoryImportFile> historyImportFiles = new RemoteHistoryImportFileDBSystem().getAllHistoryImportFiles();
        historyImportFiles = FXCollections.observableArrayList(historyImportFiles);
        System.out.println(1);
        tableViewHistoryImportFile.setItems(FXCollections.observableArrayList(historyImportFiles));
        tableColumnDayHistoryImportFile.setCellValueFactory(new PropertyValueFactory<HistoryImportFile, LocalDate>("day"));
        tableColumnTimeHistoryImportFile.setCellValueFactory(new PropertyValueFactory<HistoryImportFile, LocalTime>("time"));
        tableColumnTotalRecordHistoryImportFile.setCellValueFactory(new PropertyValueFactory<HistoryImportFile, Integer>("totalRecord"));
    }
}