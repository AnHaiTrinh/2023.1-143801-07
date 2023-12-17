package com.nhom7.login;

import com.nhom7.EmployeeContext;
import com.nhom7.entity.Employee;
import com.nhom7.login.auth.IAuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Pane paneLoginPage;
    @FXML
    private TextField employeeId;
    @FXML
    private TextField password;

    private IAuthService authService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneLoginPage.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER))
                login();
        });
    }

    @FXML
    private void login() {
        String employeeIdString = employeeId.getText();
        String passwordString = password.getText();
        Employee authenticatedEmployee = authService.authenticate(employeeIdString, passwordString);
        if (authenticatedEmployee == null) {
            onLoginFail();
        } else {
            onLoginSuccess(authenticatedEmployee);
        }
    }

    private void onLoginSuccess(Employee authenticatedEmployee) {
        EmployeeContext.create(authenticatedEmployee);
        // Login to home page
    }

    private void onLoginFail() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Login unsuccessfully");
        alert.setContentText("Please check your employee id and password");
        alert.showAndWait();
    }

    public void setAuthService(IAuthService authService) {
        this.authService = authService;
    }
}
