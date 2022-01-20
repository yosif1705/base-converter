module application {
    requires javafx.controls;
    requires javafx.fxml;

    exports controllers;
    opens controllers to javafx.fxml,javafx.controls;
    exports application;
    opens application to javafx.fxml;
}