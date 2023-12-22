package com.nhom7.login;

import com.nhom7.EmployeeContext;
import com.nhom7.alert.AlertFactory;
import com.nhom7.entity.Employee;
import com.nhom7.home.HomePageView;
import com.nhom7.import_data.ImportDataController;
import com.nhom7.login.auth.DBAuthService;
import com.nhom7.login.auth.IAuthService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Pane paneLoginPage;
    @FXML
    private TextField employeeId;
    @FXML
    private PasswordField password;

    private IAuthService authService;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        paneLoginPage.setOnKeyPressed(keyEvent -> {
//            if(keyEvent.getCode().equals(KeyCode.ENTER))
//                //login();
//        });
    }

    @FXML
    private void login(MouseEvent event) throws IOException {
        String employeeIdString = employeeId.getText();
        String passwordString = password.getText();
        if(employeeIdString.isEmpty() || passwordString.isEmpty()){
            AlertFactory.getInstance().createAlert("Error", "Tên đăng nhập hoặc mật khẩu không được để trống");
            return;
        }
        authService = new DBAuthService();
        Employee authenticatedEmployee = authService.authenticate(employeeIdString, passwordString);
        if (authenticatedEmployee == null) {
            onLoginFail();
        } else {
            onLoginSuccess(authenticatedEmployee, event);
        }
    }

    private void onLoginSuccess(Employee authenticatedEmployee, MouseEvent event) throws IOException {
        EmployeeContext.create(authenticatedEmployee);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageView.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        // Login to home page
    }

    private void onLoginFail() {
        AlertFactory.getInstance().createAlert("Error", "Login failed");
    }

    public void setAuthService(IAuthService authService) {
        this.authService = authService;
    }
}
