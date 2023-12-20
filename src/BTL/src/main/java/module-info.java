module com.example.btl {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires org.apache.poi.ooxml;

    opens com.nhom7.main to javafx.fxml;
    exports com.nhom7.main;
    opens com.nhom7.menu to javafx.fxml;
    exports com.nhom7.menu;
    opens com.nhom7.login to javafx.fxml;
    exports com.nhom7.login;
    opens com.nhom7.edit to javafx.fxml;
    exports com.nhom7.edit;
    opens com.nhom7.dbsubsystem to javafx.fxml;
    exports com.nhom7.dbsubsystem;
    opens com.nhom7.hrsubsystem to javafx.fxml;
    exports com.nhom7.hrsubsystem;
    opens com.nhom7.entity to javafx.fxml;
    exports com.nhom7.entity;
    opens com.nhom7.import_data to javafx.fxml;
    exports com.nhom7.import_data;
    exports com.nhom7.check_value;
    opens com.nhom7.check_value to javafx.fxml;
}