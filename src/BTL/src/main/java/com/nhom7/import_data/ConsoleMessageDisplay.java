package com.nhom7.import_data;

import javafx.scene.control.Alert;

public class ConsoleMessageDisplay {
    public void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
