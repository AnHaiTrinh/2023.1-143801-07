package com.nhom7.login;

import com.nhom7.login.auth.DBAuthService;
import com.nhom7.screen.ScreenSwitch;
import javafx.stage.Stage;

public class LoginView {
    private final Stage stage;

    public LoginView(Stage stage) {
        this.stage = stage;
    }
    public void show() {
        ScreenSwitch.switchScreen(
                stage,
                "/com/nhom7/login/LoginPage.fxml",
                "Login",
                new LoginController(new DBAuthService())
        );
    }
}
