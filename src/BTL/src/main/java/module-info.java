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
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

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
    opens com.nhom7.importdata to javafx.fxml;
    exports com.nhom7.importdata;
    opens com.nhom7.attendanceloglist to javafx.fxml;
    exports com.nhom7.attendanceloglist;
    opens com.nhom7.exportfile to javafx.fxml;
    exports com.nhom7.exportfile;
    exports com.nhom7.validate;
    opens com.nhom7.validate to javafx.fxml;
    exports com.nhom7.requestedit;
    opens com.nhom7.requestedit to javafx.fxml;
    exports com.nhom7;
    opens com.nhom7 to javafx.fxml;
    exports com.nhom7.home;
    opens com.nhom7.home to javafx.fxml;
}