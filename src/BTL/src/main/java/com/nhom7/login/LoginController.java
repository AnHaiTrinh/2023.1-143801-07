package com.nhom7.login;

import com.nhom7.EmployeeContext;
import com.nhom7.alert.AlertFactory;
import com.nhom7.entity.Employee;
import com.nhom7.home.HomePageView;
import com.nhom7.login.auth.IAuthService;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Pane paneLoginPage;
    public TextField employeeId;
    public TextField password;

    private IAuthService authService;

    public LoginController(IAuthService authService) {
        this.authService = authService;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneLoginPage.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER))
                login();
        });
    }

    public void login() {
        String employeeIdString = employeeId.getText();
        String passwordString = password.getText();
        if(employeeIdString.isEmpty() || passwordString.isEmpty()){
            AlertFactory.getInstance().createAlert("Error", "Tên đăng nhập hoặc mật khẩu không được để trống");
            return;
        }
        Employee authenticatedEmployee = authService.authenticate(employeeIdString, passwordString);
        if (authenticatedEmployee == null) {
            onLoginFail();
        } else {
            try {
                onLoginSuccess(authenticatedEmployee);
            } catch (IOException e) {
                e.printStackTrace();
                onLoginFail();
            }
        }
    }

    private void onLoginSuccess(Employee authenticatedEmployee) throws IOException {
        EmployeeContext.create(authenticatedEmployee);
        Stage stage = (Stage) paneLoginPage.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageView.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        // App to home page
    }

    private void onLoginFail() {

        AlertFactory.getInstance().createAlert("Error", "Đăng nhập thất bại");
    }

    public void setAuthService(IAuthService authService) {
        this.authService = authService;
    }
}
