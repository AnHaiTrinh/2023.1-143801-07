package com.nhom7.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.HashMap;
import java.util.Map;

public class AlertFactory {
    private static final AlertFactory instance = new AlertFactory();

    private static final Map<String, Alert> alertMap = new HashMap<>(){
        {
            put("Confirmation", new Alert(Alert.AlertType.CONFIRMATION));
            put("Error", new Alert(Alert.AlertType.ERROR));
            put("Information", new Alert(Alert.AlertType.INFORMATION));
            put("Warning", new Alert(Alert.AlertType.WARNING));
        }
    };

    private AlertFactory() {
    }

    public static AlertFactory getInstance() {
        return instance;
    }

    public void createAlert(String type, String content) {
        Alert alert = alertMap.get(type);
        if (alert == null) {
            throw new IllegalArgumentException("Invalid alert type");
        }
        alert.setContentText(content);
        AlertRespond.awaitRespond(alert);
    }

    public boolean createAlertAndWaitForRespond(String type, String content) {
        Alert alert = alertMap.get(type);
        if (alert == null) {
            throw new IllegalArgumentException("Invalid alert type");
        }
        alert.setContentText(content);
        return AlertRespond.isRespondOk(alert);
    }
}
