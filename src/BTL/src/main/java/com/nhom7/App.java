package com.nhom7;

import com.nhom7.login.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginView loginView = new LoginView(stage);
        loginView.show();
    }
}
