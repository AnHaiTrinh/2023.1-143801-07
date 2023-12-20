package com.nhom7.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertRespond {

    public static void awaitRespond(Alert alert) {
        alert.showAndWait();
    }
    public static boolean isRespondOk(Alert alert) {
        awaitRespond(alert);
        return alert.getResult() == ButtonType.OK;
    }
}
